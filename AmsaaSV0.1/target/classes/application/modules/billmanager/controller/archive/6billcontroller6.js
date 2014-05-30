define(function(require) {
	"use strict";
	var billViewTemplate = require('Text!../views/billmanager.html'), 
	servicePlanView = require('../model/servicePlanView'),
	billModelView = require('../model/billModelView'),
	billItemModelView=require('../model/billItemModelView'),
	customerModelView=require('../model/customerModelView'),
	srvcModelView = require('modules/servicemanager/model/serviceModelView'),
	billRepo = require('repository/billRepository'), 
	srvcRepo = require('repository/serviceRepository'), 
	viewResolver = require("ViewResolver"),
	PagedGridView = require("modules/common/model/pagedgridmodel");
	
	var billController = {
			Bill:ko.observable(''),
			
			init : function(context) {
				console.log("init BILL-CONTROLLER!!"+context);
				var billMV = new billModelView();
				billController.Bill(billMV);
				billController.Bill().init(new billItemModelView());
				//billController.Bill().initCustomer(new customerModelView());
				srvcRepo.getAllServicePlanList().then(
						function(srvcPlanLst){
							var mappedSrvcList = $.map(srvcPlanLst, function(item) {								
								return new servicePlanView(item);
							});
							console.log("mappedSrvcList "+ko.toJSON(mappedSrvcList));
							billController.Bill().servicePlanList(mappedSrvcList);
						});
				
				viewResolver.renderModelView("billCreateDiv",this, billViewTemplate,"gridStyle");
				var viewTemplRootNode = document.getElementById("billCreateForm");
				//ko.applyBindings(billMV,viewTemplRootNode);
				console.log("Ending BILL-CONTROLLER ENDinG!!");
			},
			getCustomerFromList : function(chosenFlatNo) {
				try {
					that.customer("");
					that.chosenFlatNumber(chosenFlatNo);
					$.each(that.customersList(),
							function(index, item) {
								if (item.persnAddress.flatNumber == that
										.chosenFlatNumber()) {
									that.customer(item);
								}
							});
				} catch (err) {
					alert("getCustomerList error:" + err.message);
				}
			}
	
	};

	return billController;
	
});