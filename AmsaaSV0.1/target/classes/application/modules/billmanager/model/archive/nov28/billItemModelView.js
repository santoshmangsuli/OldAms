define(function(require) {
	var billItemModelView = function() {
        var me=this; 
        me.itemProduct = ko.observable("");
		me.serviceRateItem = ko.observable();
		me.quantity = ko.observable("");
		me.subTotal = ko.computed(function() {
			return me.serviceRateItem() ? me.serviceRateItem().price()
					* parseInt("0" + me.quantity(), 10) : 0;
		});
		

	/*	me.toJSON = function() {
			var copy = ko.toJS(this);
			if (copy.itemProduct !== undefined && copy.quantity !== 0) {
				copy.srvcCode = copy.itemProduct.srvcCode;
				copy.quantity = parseInt(copy.quantity, 10);
				delete copy.itemProduct;
				delete copy.subTotal;
				return copy;
			} else
				return "";
		};*/

	};

	return billItemModelView;
});
