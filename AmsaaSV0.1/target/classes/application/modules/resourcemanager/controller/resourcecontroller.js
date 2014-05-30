define(function(require) {
	"use strict";

	var resourceViewTemplate = require('Text!../views/resourcemanager.html'), ResourceModelView = require('../model/resourceModelView'), resourceRepo = require('repository/resourceRepository'), bookingRepo = require('repository/bookingRepository'), viewResolver = require("ViewResolver"), PagedGridView = require("modules/common/model/pagedgridmodel");

	var resourceController = {

		calendar : ko.observable(''),
		resource : ko.observable(''),
		ResourceList : ko.observableArray([ "" ]),
		evtList : ko.observableArray([ "" ]),
		addNewUser : function() {
		},
		init : function() {
			console.log("init -CONTROLLER!!");
			resourceRepo.getAllResourceList().then(function(data) {
				resourceController.ResourceList(data);
			});
			resourceController.resource.subscribe(function(val) {
				if (val) {
					resourceController.selectedResourceBookings(val);
				}
			});

			viewResolver.renderModelView("resourcemanager", this,
					resourceViewTemplate, "calendarStyle");
			resourceController.cal([ "" ]);
		},

		selectedResourceBookings : function(resSel) {

			console.log("res ;" + ko.toJSON(resSel));
			bookingRepo.getAllBookingsList(resSel.resourceName).then(
					function(data) {

						var evtList = $.map(data, function(item) {

							var evt = {
								title : "Title#:"
										+ item.bookingTitle.toString(),
								start : new Date(item.startDateTime),
								end : new Date(item.endDateTime)
							};
							console.log("evt " + ko.toJSON(evt));
							return evt;
						});
						console.log("evtList: " + ko.toJSON(evtList));

						resourceController.cal(evtList);
					}).fail(function() {
				alert("Failed:");
			});
		},

		cal : function(evtList) {
			$('#resCal').empty();
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			// console.log(new Date(y, m, d-2));
			resourceController.calendar = $('#resCal').fullCalendar(
					{

						header : {
							left : 'prev,next today',
							center : 'title',
							right : 'month,agendaWeek,agendaDay'
						},
						selectable : true,
						editable : true,
						selectHelper : true,
						select : function(start, end, allDay) {

							var title = prompt('Event Title:');
							if (title) {
								var eventnew = {
									title : title,
									start : start,
									end : end,
									allDay : allDay
								};

								var booking = {
									bookingTitle : title,
									bookingPerson : {
										persnId : '16'
									},
									bookedResource : {
										resourceId : resourceController
												.resource().resourceId
									},
									startDateTime : start,
									endDateTime : end,
									allDay : allDay
								};
								bookingRepo.persistEvent(booking).then(
										function(data) {
											resourceController.calendar
													.fullCalendar(
															'renderEvent',
															eventnew, false // make
													// the
													// event
													// "stick"
													);
										}).fail(function(e) {
									alert("Error @ server side!!");
								});
							}
							// calendar.fullCalendar('unselect');

						},

						events : evtList,
						eventClick : function(event, element) {

							// event.title = "CLICKED!";
							var title = prompt('Event Title:');
							var start = prompt('Start Date:');
							if (title || start) {
								event.title = title;
								alert(event.start);
								event.start=start;
								resourceController.calendar.fullCalendar(
										'updateEvent', event);

							}

						}
					});

		}
	};

	return resourceController;

});