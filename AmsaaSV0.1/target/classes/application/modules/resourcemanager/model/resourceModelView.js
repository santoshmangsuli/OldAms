define(function(require) {
	var resourceModelView = function(data){
		
		this.dataModel = {
				resourceId : ko.observable(data.resourceId),
				resourceName : ko.observable(data.resourceName),
				resourceDescription : ko.observable(data.resourceDescription)
				
			};
		
		this.viewModel={
				isIdEditable : ko.observable(false),
				isEditable : ko.observable(false),
				isSelected : ko.observable(false)
		};
	};
	return resourceModelView;
});
