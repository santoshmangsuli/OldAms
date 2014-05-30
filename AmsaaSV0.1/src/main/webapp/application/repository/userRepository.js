define(function(require) {
	return {
		persistUser : function(usermodel) {
			console.log("persistUser "+JSON.stringify(usermodel));
		return	$.ajax({
				url : "user",
				type : "POST",
				data :ko.toJSON(usermodel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});
		},
		
		updateUser:function(usermodel){
			return	$.ajax({
				url : "user",
				type : "PUT",
				data :ko.toJSON(usermodel),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},
		
		removeUser:function(usermodel){
			console.log("removeUser "+JSON.stringify(usermodel));
			return	$.ajax({
				url : "user/"+usermodel.persnId(),
				type : "DELETE",
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},

		getAllUserList : function() {
			return $.getJSON("users"); 
		},
		
		getUserById : function(usermodel){
			//console.log("persnId "+JSON.stringify(persnId));
			return	$.ajax({
				url : "user/"+usermodel.persnId,
				type : "POST",
				dataType : "json",
				contentType : "application/json; charset=utf-8"});	
		},
		
		persistBill : function(billDTO) {
			console.log("persistBill "+ko.toJSON(billDTO));
		return	$.ajax({
				url : "Bill",
				type : "POST",
				data :ko.toJSON(billDTO),
				dataType : "json",
				contentType : "application/json; charset=utf-8"});
		}

	};

});