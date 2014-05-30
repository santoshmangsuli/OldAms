define(function(require) {
	"use strict";
	var billViewTemplate = require('Text!../views/billmanager.html'),
	billDashBoardTemplate = require('Text!../views/billdashboard.html'),
	billModelView = require('../model/billModelView'),
	billItemModelView=require('../model/billItemModelView'),
	customerModelView=require('../model/customerModelView'),
	srvcModelView = require('modules/servicemanager/model/serviceModelView'),
	billRepo = require('repository/billRepository'), 
	srvcRepo = require('repository/serviceRepository'), 
	viewResolver = require("ViewResolver"),
	BillPagedGridView = require("modules/common/model/pagedgridmodel"),
	servicePlanView = require('../model/servicePlanView');
	
	var billController = {
			Bill:ko.observable(''),
			billPagedGridView : ko.observable(''),
			
			init : function(context) {
				console.log("init BILL-CONTROLLER!!"+context);
				billController.Bill(new billModelView());
				billController.Bill().init(new billItemModelView());
				srvcRepo.getAllServicePlanList().then(
						function(srvcPlanLst){
							var mappedSrvcList = $.map(srvcPlanLst, function(item) {								
								return new servicePlanView(item);
							});
							//console.log("mappedSrvcList "+ko.toJSON(mappedSrvcList));
							billController.Bill().servicePlanList(mappedSrvcList);
						});
				
				billRepo.getBillDetails().then(
						function(billData) {
							
							var mappedBillList = $.map(billData, function(item) {
								//console.log("item "+JSON.stringify(item));
								var billDataModel = {
										persnFirstName : item.billedPerson.persnFirstName,
										persnLastName  : item.billedPerson.persnLastName,
										flatNumber : item.billedPerson.persnAddress.flatNumber,
										billDate : item.billDate,
										billDueDate : item.billDueDate,
										billPaymentStatus : item.billPaymentRegister.billPaymentStatus
								};
								return billDataModel;
							});
							
							billController.billPagedGridView(new BillPagedGridView(
									mappedBillList, [ "1","5", "10", "15" ],
									"5"));
						});
							
				viewResolver.renderModelView("billCreateDiv",this, billViewTemplate,"gridStyle");
				viewResolver.renderModelView("billDashBoardDiv",this, billDashBoardTemplate,"gridStyle");

				console.log("init BILL-CONTROLLER ENDinG!!");
			}
	};

	return billController;
	
});