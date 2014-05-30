define(function(require) {
	"use strict";

	var viewResolver = require("ViewResolver"), 
	srvcViewTemplate = require('Text!../views/services.html'), 
	SrvcModelView = require('../model/serviceModelView'), 
	srvcRepo = require('repository/serviceRepository'), 
	PagedGridView = require("modules/common/pagedGridModel");

	var srvcController = {

		srvcPagedGridView : ko.observable(''),

		init : function() {
			
			console.log("Initialize Service controller");
			srvcRepo.getAllServiceList().then(
					function(srvcData) {
						console.log("srvcData "+JSON.stringify(srvcData));
						var mappedSrvcList = $.map(srvcData, function(item) {
							return new SrvcModelView(item);
						});
						
						srvcController.srvcPagedGridView(new PagedGridView(
								mappedSrvcList, [ "1","5", "10", "15" ],
								"5"));
					});

			viewResolver.renderModelView("servicemanager", this,
					srvcViewTemplate, "srvcMgrStyle");
		},

		addNewService : function() {
			var svcModelView = new SrvcModelView("");
			svcModelView.viewModel.isSelected(true);
			svcModelView.viewModel.isIdEditable(true);
			svcModelView.viewModel.isEditable(true);
			srvcController.srvcPagedGridView().dataRepo.push(svcModelView);
			srvcController.srvcPagedGridView().moveToLastPage();
		},

		deleteService : function(data) {
			if (data !== "") {
			srvcController.srvcPagedGridView().dataRepo.remove(data);
			srvcRepo.removeService(data.dataModel);
			srvcController.srvcPagedGridView().moveToLastPage();
			}
			
		},

		saveService : function(data) {
			if (data !== "") {
				if (data.viewModel.isIdEditable() === true) {
					data.viewModel.isIdEditable(false);
					data.viewModel.isEditable(false);
					srvcRepo.persistService(data.dataModel).then(function(serverData){
						var mappedSrvcList = $.map(serverData, function(item) {
								return new SrvcModelView(item);
							});
							srvcController.srvcPagedGridView().dataRepo.removeAll();
							srvcController.srvcPagedGridView().dataRepo(mappedSrvcList);
					});
				} 
				else if(data === "")
				{
					srvcController.srvcPagedGridView().dataRepo.remove(data);
				}	
				else {
					data.viewModel.isEditable(false);
					srvcRepo.updateService(data.dataModel);
				}
			}

		}

	};

	return srvcController;
});