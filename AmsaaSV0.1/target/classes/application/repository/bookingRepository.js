define(function(require) {
	return {
		
		persistEvent : function(eventmodel) {
			console.log("persistEvent "+JSON.stringify(eventmodel));
		return	$.ajax({
				url : "bookings",
				type : "POST",
				data :ko.toJSON(eventmodel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});
		},

		getAllBookingsList : function(resourceName) {
			return $.getJSON("bookings/"+resourceName); 
		}

	};

});