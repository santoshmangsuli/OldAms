define(function(require) {
	var billLineItemsHolder = function() {
		var me=this;
		me.itemProduct = ko.observable();
		me.billLineItems = ko.observableArray([new billItemModelView()]);
		
	};

	return billLineItemsHolder;
});