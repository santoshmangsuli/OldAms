define(function(require) {
	var userModelView = function(data){
		
		if(data != null) {
		this.dataModel = {
				persnId : ko.observable(data.persnId),
				persnFlatNum : ko.observable(data.persnAddress.flatNumber),
				persnFirstName : ko.observable(data.persnFirstName),
				persnLastName : ko.observable(data.persnLastName),
				
				persnPhoneNum : ko.observable(data.persnDetail.landLineNumber),
				persnMobileNum : ko.observable(data.persnDetail.mobileNumber),
				persnEmailId : ko.observable(data.persnDetail.emailId),
				apartmentName: ko.observable(data.persnDetail.apartmentName),
				street: ko.observable(data.persnDetail.street),
				landMark: ko.observable(data.persnDetail.landMark),
				city: ko.observable(data.persnDetail.city)
				
			};
		
		this.viewModel={
				isIdEditable : ko.observable(false),
				isEditable : ko.observable(false),
				isSelected : ko.observable(false)
		};
		}else{
			this.dataModel = {
					persnFlatNum : ko.observable('Flat Number'),
					persnFirstName : ko.observable('Person First Name'),
					persnLastName : ko.observable('Person Last  Name '),
					persnPhoneNum : ko.observable('Person Phone Num'),
					persnMobileNum : ko.observable('Person Mobile Num'),
					persnEmailId : ko.observable('persn Email Id'),
					apartmentName : ko.observable('Apartment Name'),
					street : ko.observable('street'),
					landMark : ko.observable('LandMark'),
					city : ko.observable('City')
				};
		}
	};
	return userModelView;
});
