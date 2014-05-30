//requirejs configurations
require.config({
	/*
	 * AMD load by name configuration
	 */
	paths : {
		Application : '../lib/raghs-core/application',
		Dispatcher : '../lib/raghs-core/dispatcher',
		ViewResolver : '../lib/raghs-core/viewresolver',
		View : '../lib/raghs-core/view',
		UrlMap : '../lib/raghs-core/urlmap',
		ViewStyleMap : '../lib/raghs-core/viewStyleMap',
		Text : '../lib/amd/text',
		calendar:'../lib/fullcalendar/fullcalendar.min.js'
	}
});

define(function(require) {
	"use strict";

	var application = require("Application");

	// Start application once DOM gets loaded
	$(function() {
		application.start();
	});

});