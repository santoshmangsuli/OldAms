/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description: 
 *  
 */

define(function(require) {
	"use strict";
	return {
		"#home"				: require("./modules/homemanager/controller/homecontroller"),
		"#people"		    : require("./modules/usermanager/controller/usercontroller"),
		"#servicemanager"	: require("./modules/servicemanager/controller/servicecontroller"),
		"#financemanager"	: require("./modules/financemanager/controller/financecontroller"),		
		"#billCreateDiv"	: require("modules/billmanager/controller/billcontroller"),
		"#billDashBoardDiv"	: require("modules/billmanager/controller/billcontroller"),
		"#resourcemanager"  : require("modules/resourcemanager/controller/resourcecontroller"),
		"tableGridStyle"	: "/lib/jquery/jquery-grid/css/ui.jqgrid.css"
	};
});