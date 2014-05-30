define(function(require) {
	"use strict";

	var userViewTemplate = require('Text!../views/usermanager.html'), 
		UserModelView = require('../model/userModelView'), 
		userRepo = require('repository/userRepository'), 
		viewResolver = require("ViewResolver"),
		PagedGridView = require("modules/common/model/pagedgridmodel");
		

	var userController = {

		userPagedGridView : ko.observable(''),
		UserModelView : new UserModelView(null),
		addNewUser : function() {
			console.log("data "+JSON.stringify(this.UserModelView.dataModel.persnFirstName()));
			var data = {
		            	persnFirstName : this.UserModelView.dataModel.persnFirstName(), 
		            	persnLastName : this.UserModelView.dataModel.persnLastName(),
		            	persnDetail : {
		            		landLineNumber : this.UserModelView.dataModel.persnPhoneNum(),
		            		mobileNumber : this.UserModelView.dataModel.persnMobileNum(),
		            		emailId : this.UserModelView.dataModel.persnEmailId()
		            	},
		            	persnAddress : {
		            		flatNumber : this.UserModelView.dataModel.persnFlatNum(),
		            		apartmentName : this.UserModelView.dataModel.apartmentName(),
		            		street : this.UserModelView.dataModel.street(),
		            		landMark : this.UserModelView.dataModel.landMark(),
		            		city : this.UserModelView.dataModel.city()
		            			
		            	}
		            };
			userRepo.persistUser(data);
			console.log("data 1"+JSON.stringify(data));
		},
		init : function() {
			console.log("init USER-CONTROLLER!!");
			userRepo.getAllUserList()
					.then(
							function(usrData) {
								var mappedUserList = $.map(usrData, function(item) {
									var usr = new UserModelView(item);
									return usr;
								});
								userController.userPagedGridView(new PagedGridView(
									mappedUserList, [ "1", "5","10", "15" ], "5"));
							});
			ko.applyBindings(UserModelView);
			console.log("init USER-CONTROLLER eNDinG!!");
			viewResolver.renderModelView("people", this, userViewTemplate,
					"userMgrStyle");	

		},
		removeUser : function(data) {
			console.log("removeUser data "+JSON.stringify(data.dataModel));
			if (data !== "") {
				userController.userPagedGridView().dataRepo.remove(data);
				userRepo.removeUser(data.dataModel);
				userController.userPagedGridView().moveToLastPage();
			}
		},

		saveUser : function(data) {
			console.log("saveUser data "+JSON.stringify(data.dataModel));
			if (data !== "") {
				if (data.viewModel.isIdEditable() === true) {
					console.log("saveUser isIdEditable ");
					data.viewModel.isIdEditable(false);
					data.viewModel.isEditable(false);
					userRepo.persistUser(data.dataModel).then(function(serverData){
						var mappedUserList = $.map(serverData, function(item) {
								return new UserModelView(item);
							});
						userController.srvcPagedGridView().dataRepo.removeAll();
						userController.srvcPagedGridView().dataRepo(mappedUserList);
					});
				} 
				else if(data === "")
				{
					userController.srvcPagedGridView().dataRepo.remove(data);
				}	
				else {
					console.log("saveUser isEditable ");
					data.viewModel.isEditable(false);
					var person = {
							persnId : data.dataModel.persnId(),
			            	persnFirstName : data.dataModel.persnFirstName(), 
			            	persnLastName : data.dataModel.persnLastName(),
			            	persnDetail : {
			            		landLineNumber : data.dataModel.persnPhoneNum(),
			            		mobileNumber : data.dataModel.persnMobileNum(),
			            		emailId : data.dataModel.persnEmailId()
			            	},
			            	persnAddress : {
			            		flatNumber : data.dataModel.persnFlatNum(),
			            		apartmentName : data.dataModel.apartmentName(),
			            		street : data.dataModel.street(),
			            		landMark : data.dataModel.landMark(),
			            		city : data.dataModel.city()
			            			
			            	}
			            };
					userRepo.updateUser(person);
				}
			}

		}
	}	;

	return userController;

});