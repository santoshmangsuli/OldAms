/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description: This script file contains view models code.
 *  
 */

var CustomerModel = function(chosenFlatNo, customersList) {
	var that = this;
	that.chosenFlatNumber = ko.observable(chosenFlatNo);
	that.customersList = ko.observableArray(customersList);
	that.customer = ko.observable("");

	that.getCustomerFromList = function() {
		try {
			that.customer("");
			$.each(that.customersList(), function(index, item) {
				if (item.persnAddress.flatNumber == that.chosenFlatNumber()) {
					that.customer(item);
				}
			});
		} catch (err) {
			alert("getCustomerList error:" + err.message);
		}
	};

	that.removeCustomer = function() {
		$.post("removeUserAction.action", {
			"user.persnId" : this.persnId
		});
		that.customersList.remove(this);
	};
};

var ServiceViewModel = {
	srvcList : ko.observableArray([ "" ]),

	saveChange : function(srvc) {
		var data = {"aptService" : srvc};
		$.ajax({
			url : "updateServiceDetailAction.action",
			type : "POST",
			data : ko.toJSON(data),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				var mappedSrvcList = $.map(data, function(item) {
					return new ServiceModel(item);
				});
				ko.mapping.updateFromJS(ServiceViewModel.srvcList,
						mappedSrvcList);
			}
		});
	},

	addNewService : function() {
		ServiceViewModel.srvcList.push(new ServiceModel(this));
	},

	removeService : function(srvc) {
		var data = {
			"aptService" : srvc
		};
		ServiceViewModel.srvcList.remove(srvc);
		$.ajax({
			url : "deleteServiceAction.action",
			type : "POST",
			data : ko.toJSON(data),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				var mappedSrvcList = $.map(data, function(item) {
					return new ServiceModel(item);
				});
				ko.mapping.updateFromJS(ServiceViewModel.srvcList,
						mappedSrvcList);
			}
		});
	}
};

function ServiceModel(data) {
	var that = this;
	that.srvcCode = ko.observable(data.srvcCode);
	that.srvcName = ko.observable(data.srvcName);
	that.srvcDescription = ko.observable(data.srvcDescription);
	that.isSaved = ko.observable(false);
	that.isEdited = ko.observable(false);
	that.isDeleted = ko.observable(false);

	that.isSaved.subscribe(function() {
		if (that.isEdited() === true) {
			that.isEdited(false);
			ServiceViewModel.saveChange(that);
		}
	});

	that.isDeleted.subscribe(function() {
		ServiceViewModel.removeService(that);
	});

	that.toJSON = function() {
		var srvcCopy = ko.toJS(that);
		delete srvcCopy.isEdited;
		delete srvcCopy.isSaved;
		delete srvcCopy.isDeleted;
		return srvcCopy;
	};
}

function BillItemModel() {
	var me = this;
	me.itemProduct = ko.observable("");
	me.quantity = ko.observable(1).extend({
		required : true
	}).extend({
		number : true
	});
	me.subTotal = ko.computed(function() {
		return me.itemProduct() ? me.itemProduct().srvcPrice
				* parseInt("0" + me.quantity(), 10) : 0;
	});

	me.quantity.subscribe(function() {
		BillModel.calculateTotal();
	});

	me.itemProduct.subscribe(function() {
		BillModel.calculateTotal();
	});

	me.toJSON = function() {
		var copy = ko.toJS(this);
		if (copy.itemProduct !== undefined && copy.quantity !== 0) {
			copy.srvcCode = copy.itemProduct.srvcCode;
			copy.quantity = parseInt(copy.quantity, 10);
			/*
			 * copy.LineItem = { srvcCode : copy.itemProduct.srvcCode, quantity :
			 * parseInt(copy.quantity, 10) }; delete copy.quantity;
			 */
			delete copy.itemProduct;
			delete copy.subTotal;
			return copy;
		}
	};
}

ko.validation.registerExtenders();

ko.validation.rules['greaterThan'] = {
	validator : function(val, otherVal) {
		return val > otherVal();
	},
	message : 'The field must be greater than {0}'
};

var BillModel = {
	srvcModel : ServiceViewModel,
	billCustomer : ko.observable(new CustomerModel("", [])),
	billLineItems : ko.observableArray([ new BillItemModel() ]),
	billDate : ko.observable(new Date()),
	billDueDate : ko.observable(new Date()).extend({
		greaterThan : this.billDate
	}),
	billPeriodFromDate : ko.observable(new Date()),
	billPeriodToDate : ko.observable(new Date()),
	billTotalTax : ko.observable(0),
	billTotalAmount : ko.observable(0),

	addBillItem : function() {
		try {
			this.billLineItems.push(new BillItemModel());
		} catch (err) {
			alert("error:" + err.message);
		}
	},

	removeBillItem : function() {
		try {
			BillModel.billLineItems.remove(this);
			BillModel.calculateTotal();
		} catch (err) {
			alert("error:" + err.message);
		}
	},

	calculateTotal : function() {
		try {
			var data = {
				"billSrvcData" : BillModel
			};
			$.ajax({
				url : "addBillItemsAction.action",
				type : "POST",
				data : ko.toJSON(data),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					BillModel.billTotalAmount(data.billTotalAmount);
					BillModel.billTotalTax(data.billTotalTax);
				}
			});

		} catch (err) {
			alert("error:" + err.message);
		}

	},

	submitBill : function() {
		try {

			var data = {
				"billSrvcData" : BillModel
			};

			$.ajax({
				url : "submitBillAction.action",
				type : "POST",
				data : ko.toJSON(data),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					BillModel.billTotalAmount(data.billTotalAmount);
					BillModel.billTotalTax(data.billTotalTax);
				}
			});

		} catch (err) {
			alert("error:" + err.message);
		}

	},

	resetBill : function() {
		this.billDate.reset();
		this.billDueDate.reset();
		this.billPeriodFromDate.reset();
		this.billPeriodToDate.reset();
		this.billCustomer.reset();
		this.billLineItems.reset();
	},

	toJSON : function() {
		var clone = ko.toJS(BillModel);
		var items = [];
		$.each(clone.billLineItems, function(key, item) {
			if (item.itemProduct && item.quantity > 0) {
				items.push(item);
			}
		});

		clone.billLineItems = items;
		clone.customerId = clone.billCustomer.customer.persnId;
		delete clone.billCustomer;
		delete clone.prodList;
		delete clone.billTotalTax;
		delete clone.billTotalAmount;
		return clone;
	}
};

ko.bindingHandlers.datepicker = {
	init : function(element, valueAccessor, allBindingsAccessor) {

		/*
		 * Initialize datepicker with some optional options
		 */

		$(element).datepicker({
			dateFormat : 'd-M-yy'
		});

		/*
		 * Handle the field changing
		 */
		ko.utils.registerEventHandler(element, "change", function() {
			var observable = valueAccessor();
			$(element).datepicker({
				dateFormat : 'd-M-yy'
			});
			observable($(element).datepicker("getDate"));
		});

	},
	update : function(element, valueAccessor) {
		var value = ko.utils.unwrapObservable(valueAccessor()), current = $(
				element).datepicker("getDate");

		if (value - current !== 0) {
			$(element).datepicker("setDate", value);
		}
	}

};
