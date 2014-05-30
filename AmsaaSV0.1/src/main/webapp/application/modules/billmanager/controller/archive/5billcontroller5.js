define(function(require) {
	"use strict";
	var billViewTemplate = require('Text!../views/billmanager.html'), 
	billModelView = require('../model/billModelView'),
	srvcModelView = require('modules/servicemanager/model/serviceModelView'),
	billRepo = require('repository/billRepository'), 
	srvcRepo = require('repository/serviceRepository'), 
	viewResolver = require("ViewResolver"),
	PagedGridView = require("modules/common/model/pagedgridmodel");
	
	var billController = {
			BillModelView : new billModelView(null),
			init : function() {
				
				console.log("init BILL-CONTROLLER!!");

				srvcRepo.getAllServiceList().then(
						function(srvcData) {
							var list = $.map(srvcData, function(item) {
								var srvcModel = new srvcModelView(item);
								console.log(" srvcModel :"+ko.toJSON(srvcModel));
								return srvcModel;
							});
							billController.BillModelView.prodList(list);
						});
				viewResolver.renderModelView("billpayments", this, billViewTemplate,
						"gridStyle");

				console.log("init BILL-CONTROLLER ENDinG!!");
			}
	};

	return billController;
	
});