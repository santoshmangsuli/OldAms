define(function(require) {
	var customerModelView = function(customersList) {
		var that = this;
		that.chosenFlatNumber = ko.observable("");
		that.customersList = ko.observableArray(customersList);
		that.customer = ko.observable("");
		that.persnFirstName = ko.observable("");
		that.persnLastName = ko.observable("");
		that.mobileNumber = ko.observable("");
		that.emailId = ko.observable("");

		

		that.removeCustomer = function() {
			$.post("removeUserAction.action", {
				"user.persnId" : this.persnId
			});
			that.customersList.remove(this);
		};

	};
	
	return customerModelView;
});
