/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description:	This script file contains page control related functions like binding data to views and communicating with server.
 */

$(function() {

	initPage();

	$.getJSON("getUsersListAction.action", function(data) {
		BillModel.billCustomer().customersList(data.users);
	});

	$.getJSON("getServicesListAction.action", function(data) {
		var mappedSrvcList = $.map(data, function(item) {
			return new ServiceModel(item);
		});
		ServiceViewModel.srvcList.removeAll();
		ServiceViewModel.srvcList(mappedSrvcList);
	});

	ko.applyBindings(BillModel.billCustomer(), document
			.getElementById("userManagerTabs"));

	ko.applyBindings(ServiceViewModel, document
			.getElementById("billableServicesContainer"));

	ko.applyBindings(BillModel, document.getElementById("billCreateForm"));

});
