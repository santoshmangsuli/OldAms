define(function(require) {
	var serviceRateView = function(data) {
		var self = this;
		if(data.service != null){
		self.srvcCode = ko.observable(data.service.srvcCode);
		self.srvcName = ko.observable(data.service.srvcName);
		self.price = ko.observable(data.srvcChargeComponent.chargeRate.price.amount);
		}	
		
	};
	return serviceRateView;
});