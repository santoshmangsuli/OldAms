/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description: 
 *  
 */

define(function(require) {
	var styleLinkMap = require("ViewStyleMap");
	return {
		renderModelView : function(parentElement, model, viewTemplate,
				viewStyleUrl) {
			
			
			if (document.getElementById(parentElement).childNodes.length === 0) {
				console.log("renderviewmodel");
				this.renderViewStyle(viewStyleUrl);
				$("#" + parentElement).append(viewTemplate);
				if (model) {
					
					var viewTemplRootNode = document.getElementById(parentElement).childNodes[0];
					console.log("renderModelView!!"+viewTemplRootNode);
					ko.applyBindings(model, viewTemplRootNode);
				}
			}
		},

		renderViewStyle : function(styleUrl) {
			var link = document.createElement("link");
			link.type = "text/css";
			link.rel = "stylesheet";
			link.href = styleLinkMap[styleUrl];
			document.getElementsByTagName("head")[0].appendChild(link);
		}

	};
});