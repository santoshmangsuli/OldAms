define(function(require) {
	var billModelView = function(data){
		var self = this;
		console.log("billModelView created !!");
		self.CustomerModel = function(chosenFlatNo, customersList) {
			console.log("CustomerModel created !!");
			var that = this;
			that.chosenFlatNumber = ko.observable(chosenFlatNo);
			that.customersList = ko.observableArray(customersList);
			that.customer = ko.observable("");
			that.persnFirstName = ko.observable("");
			that.persnLastName = ko.observable("");
			that.mobileNumber  = ko.observable("");
			that.emailId = ko.observable("");

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

		self.BillItemModel = function () {
			var me = this;
			me.itemProduct = ko.observable("");
			me.quantity = ko.observable(1);
			me.subTotal = ko.computed(function() {
				return me.itemProduct() ? me.itemProduct().srvcPrice
						* parseInt("0" + me.quantity(), 10) : 0;
			});

			me.toJSON = function () {
				var copy = ko.toJS(this);
				if (copy.itemProduct !== undefined && copy.quantity !== 0) {
					copy.srvcCode = copy.itemProduct.srvcCode;
					copy.quantity = parseInt(copy.quantity, 10);
					delete copy.itemProduct;
					delete copy.subTotal;
					return copy;
				} else
					return "";
			};
		};
	
		self.prodList = ko.observableArray([""]);
		self.billDate = ko.observable("Select Bill Date..");
		self.billDueDate = ko.observable("Select Bill Due-Date..");
		self.billPeriodFromDate = ko.observable("Select Bill From Date..");
		self.billPeriodToDate = ko.observable("Select Bill To Date..");
		self.billCustomer = ko.observable(new self.CustomerModel("", []));
		self.billLineItems = ko.observableArray([new self.BillItemModel()]);
		self.billTotalTax = ko.observable(0);
		self.billTotalAmount = ko.observable(0);
		

		self.addBillItem = function() {
			self.billLineItems.push(new BillItemModel());
		};
		
		self.submitBill = function() {
		};

		self.resetBill = function() {
		};
		self.removeBillItem= function() {
			console.log("removeBillItem  !!");
		};
	};
	return billModelView;
});
