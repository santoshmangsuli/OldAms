define(function(require) {
	serviceRateView = require('../model/serviceRateView');
	var servicePlanView = function(data) {
		console.log("servicePlanView  "+JSON.stringify(data.srvcPlanName));
		var self = this;
		self.srvcPlanName = ko.observable(data.srvcPlanName);
		var mappedSrvcRateList = $.map(data.serviceRateSet, function(item) {								
			return new serviceRateView(item);
		});
		self.srvcRateLst = ko.observableArray(mappedSrvcRateList);
		
//		self.createServiceRateMap = function(serviceRateSet) {
//			$.post("removeUserAction.action", {
//				"user.persnId" : this.persnId
//			});
//			that.customersList.remove(this);
//		};
//		
	};
	return servicePlanView;
});
