<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Apartment Management Software</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/application/styles/custom-theme/images/favicon.ico" />
<link
	href="${pageContext.request.contextPath}/application/styles/custom-theme/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/application/styles/applicationStyle.css"
	rel="stylesheet" />

<script
	src="${pageContext.request.contextPath}/application/lib/jquery/jquery-1.8.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/application/lib/jquery/jquery-ui-1.9.2.custom.min-1.js"></script>
<script
	src="${pageContext.request.contextPath}/application/lib/knockout/knockout-latest.js"></script>
<script
	src="${pageContext.request.contextPath}/application/lib/knockout/knockout.validation.min.js"></script>

<script
	src="${pageContext.request.contextPath}/application/viewmodel/model.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/application/viewmodel/view.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/application/controller/controller.js"
	type="text/javascript"></script>
</head>

<body>
	<div id="header"></div>
	<div id="content">
		<!-- Authorise each tab based on users access to access it -->

		<div id="mainPageTabs" class="customTabs">
			<ul>
				<li><a href="#1">USER MANAGER</a></li>
				<li><a href="#2">FINANCE MANAGER</a></li>
				<li><a href="#3">BILLS &amp; PAYMENTS MANAGER</a></li>
				<li><a href="#4">APPOINTMENTS MANAGER</a></li>
			</ul>
			<div id="1">
				<%@include file="/application/views/usermanager.html"%>
			</div>
			<div id="2">
				<%@include file="/application/views/financemanager.html"%>
			</div>
			<div id="3">
				<%@include file="/application/views/billmanager.html"%>
			</div>
			<div id="4">
				<%@include file="/application/views/calendarmanager.html"%>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
