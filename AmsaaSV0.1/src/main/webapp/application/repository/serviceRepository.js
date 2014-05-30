define(function(require) {
	return {
		persistService : function(servicemodel) {
		return	$.ajax({
				url : "service",
				type : "POST",
				data :ko.toJSON(servicemodel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});
		},
		
		updateService:function(serviceModel){
			return	$.ajax({
				url : "service",
				type : "PUT",
				data :ko.toJSON(serviceModel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},
		
		removeService:function(serviceModel){
			return	$.ajax({
				url : "service/"+serviceModel.srvcCode(),
				type : "DELETE",
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},

		getAllServiceList : function() {
			return $.getJSON("services"); 
		},

		getAllServicePlanList : function() {
			return $.getJSON("serviceplans"); 
		}

	};

});