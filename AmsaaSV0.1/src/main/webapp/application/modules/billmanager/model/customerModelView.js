define(function(require) {
	userRepo = require('repository/userRepository');
	var customerModelView = function() {
		var that = this;
		that.chosenFlatNumber = ko.observable("");
		
		that.customer = ko.observable("");
		that.persnFirstName = ko.observable("");
		that.persnLastName = ko.observable("");
		that.landLineNumber = ko.observable("");
		that.mobileNumber = ko.observable("");
		that.emailId = ko.observable("");

		that.getCustomerFromList = function(data) {
			try {
				var userdata = {
						persnId : data.chosenFlatNumber()
				};
				console.log("userdata "+ko.toJSON(userdata));
				var retCust = null;
				userRepo.getUserById(userdata).then(
						function(custdata){
							console.log("custdata "+ko.toJSON(custdata));
							that.customer(custdata); 
							that.persnFirstName(custdata.persnFirstName);
							that.persnLastName(custdata.persnLastName);
							that.landLineNumber(custdata.persnDetail.landLineNumber);
							that.mobileNumber(custdata.persnDetail.mobileNumber);
							that.emailId(custdata.persnDetail.emailId);
						});;
				console.log("chosenFlatNo "+ko.toJSON(retCust));
				that.customer("");
				
				//that.chosenFlatNumber(chosenFlatNo);
				
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
	
	return customerModelView;
});
