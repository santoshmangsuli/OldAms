/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description: 
 *  
 */

define(function(require) {
	/*
	 * To avoid accidental global variable creation
	 */
	"use strict";

	var urlController = require("Dispatcher");

	var app = {
		context : this,
		start : function() {
			console.log("Application starting");
			urlController.init();
			urlController.forward('home');
			console.log("Application started");
		}
	};
	return app;
});