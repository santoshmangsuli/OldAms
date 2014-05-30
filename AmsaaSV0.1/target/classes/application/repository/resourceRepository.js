define(function(require) {
	return {
		persistResource : function(resourcemodel) {
			console.log("persistUser "+JSON.stringify(resourcemodel));
		return	$.ajax({
				url : "resource",
				type : "POST",
				data :ko.toJSON(resourcemodel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});
		},
		
		updateResource:function(resourcemodel){
			return	$.ajax({
				url : "resource",
				type : "PUT",
				data :ko.toJSON(resourcemodel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},
		
		removeResource:function(resourcemodel){
			console.log("removeresource "+JSON.stringify(resourcemodel));
			return	$.ajax({
				url : "resource/"+resourcemodel.resourceId(),
				type : "DELETE",
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},

		getAllResourceList : function() {
			return $.getJSON("resources"); 
		},
		
		getResourceById : function(resourcemodel){
			//console.log("persnId "+JSON.stringify(persnId));
			return	$.ajax({
				url : "resource/"+resourcemodel.resourceId,
				type : "POST",
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		}

	};

});