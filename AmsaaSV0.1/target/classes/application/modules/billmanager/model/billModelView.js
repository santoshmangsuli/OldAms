define(function(require) {
	billItemModelView=require('../model/billItemModelView'),
	servicePlanView = require('../model/servicePlanView');
	customerModelView = require('../model/customerModelView');
	userRepo = require('repository/userRepository');
	var billModelView = function() {
		var self = this;
		self.billDate = ko.observable("Select Bill Date..");
		self.billDueDate = ko.observable("Select Bill Due-Date..");
		self.billPeriodFromDate = ko.observable("Select Bill From Date..");
		self.billPeriodToDate = ko.observable("Select Bill To Date..");
		self.customersList=ko.observableArray([""]);
		self.billCustomer = ko.observable(new customerModelView());
		self.servicePlanView = ko.observable("");
		self.prodList = ko.observable("");
		self.billLineItems = ko.observableArray([]);
		self.billTotalTax = ko.observable(0);
		self.billTotalAmount = ko.observable(0);
		self.servicePlanList = ko.observableArray();
		
		this.billLineItems.subscribe(function() {
			self.calculateTotal();
		});
		
		self.init=function(data){
			self.billLineItems().push(data);
		};
		
		self.addBillItem = function() {	
			self.billLineItems.push(new billItemModelView());
		};
		
		self.removeBillItem = function() {
			self.billLineItems.remove(this);
		};
		
		self.submitBill = function(data) {
			console.log("submit Bill "+data.Bill());
			//var bill = data.Bill();
			var billDTO =  {
					billDate : data.Bill().billDate(),
					billDueDate : data.Bill().billDueDate(),
					billPeriodFromDate : data.Bill().billPeriodFromDate(),
					billPeriodToDate : data.Bill().billPeriodToDate(),
					billTotalTax		: data.Bill().billTotalTax(),
					billTotalAmount	 : data.Bill().billTotalAmount(),
					customerId : data.Bill().billCustomer().chosenFlatNumber(),
					billLineItems : data.Bill().genBillItems(data.Bill())
			

			};
			userRepo.persistBill(billDTO);
		};
		
		self.genBillItems = function(data){
			var items = data.billLineItems();
			var retItems = [items.length];
			for (var key in items) {
			    var obj = items[key];
			    retItems[key] = {
			    		billItemAmount : obj.itemProduct().price(),
			    		billItemQuantity : obj.quantity(),
			    		billItemService : { srvcCode : obj.itemProduct().srvcCode()}
			    };
			 }
			 return retItems;
		};

		self.resetBill = function() {
		};
		
		
		self.calculateTotal = function() {
				var items = [];
				var totalAmt = 0;
							
				var data = self.billLineItems();
				$.each(data, function(i,item) {
					//console.log("item "+ko.toJSON(data[i].subTotal));
					if (data[i] != undefined) {
						items.push(item);
						totalAmt+=data[i].subTotal();
					}
				});
				
				self.billTotalAmount(totalAmt);
				/*$.getJSON("addBillItemAction.action", {
					jsonItems : ko.toJSON(self.billLineItems)
				}, function(data) {
					if (!isNaN(data.bill.billTotalAmount)) {
						self.billTotalTax(data.bill.billTotalTax);
						self.billTotalAmount(data.bill.billTotalAmount);
					}
				});*/
			};
	};
	return billModelView;
});
