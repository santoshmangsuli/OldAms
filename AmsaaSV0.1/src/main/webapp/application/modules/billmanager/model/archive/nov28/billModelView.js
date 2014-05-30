define(function(require) {
	billItemModelView=require('../model/billItemModelView');
	
	var billModelView = function() {
		var self = this;
		self.billDate = ko.observable("Select Bill Date..");
		self.billDueDate = ko.observable("Select Bill Due-Date..");
		self.billPeriodFromDate = ko.observable("Select Bill From Date..");
		self.billPeriodToDate = ko.observable("Select Bill To Date..");
		self.customersList=ko.observableArray([""]);
		self.billCustomer = ko.observable("");
		self.prodList = ko.observableArray();
		self.billLineItems = ko.observableArray();
		self.billTotalTax = ko.observable(0);
		self.billTotalAmount = ko.observable(0);
		self.servicePlanList = ko.observableArray();
		self.itemProduct = ko.observable();

		self.init=function(data){
			self.billLineItems().push(data);
		};
		self.initCustomer=function(data){
			
			self.billCustomer = data;
		};
		
		self.addBillItem = function() {
			console.log("self.billLineItems() "+ko.toJSON(self.billLineItems()));
			//var billItem = new billItemModelView();
			//billItem.serviceRateItem = new serviceRateView();
			self.billLineItems().push(new billItemModelView());
			console.log("self.billLineItems() "+ko.toJSON(self.billLineItems()));
		};

		self.submitBill = function() {
		};

		self.resetBill = function() {
		};
		
		self.initSrvcRateMap = function(data) {
			console.log("initSrvcRateMap "+ko.toJSON(data));
			self.servicePlanList(data);
			console.log("self.servicePlanList "+ko.toJSON(self.servicePlanList));
		};
	};
	return billModelView;
});
