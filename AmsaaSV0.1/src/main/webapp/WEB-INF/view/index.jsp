<!--
 * 
 *   Document   : Application Style
 *   Author     : Raghavendra Badiger
 *   Description: "HOME" page JSP which serves as template for website
 -->
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<title>AmsaaS</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width-device-width,initial-scale=1.0" />

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/lib/bootstrap/img/leaf.ico" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap-responsive.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/application/styles/glyph.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/application/styles/application.css" />
<link
	href="${pageContext.request.contextPath}/lib/jquery/jquery-ui/custom-theme/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/fullcalendar/fullcalendar.print.css" rel="stylesheet" media="print" />	
<link href="${pageContext.request.contextPath}/lib/fullcalendar/fullcalendar.css" rel="stylesheet" />

	
<script
	src="${pageContext.request.contextPath}/lib/jquery/jquery-1.8.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/jquery/jquery-ui-1.9.2.custom.min-1.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/knockout/knockout-latest.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/knockout/knockout.validation.min.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/knockout/knockout.datepicker.js"></script>

<script src="${pageContext.request.contextPath}/lib/amd/require.js"
	data-main="${pageContext.request.contextPath}/application/main"></script>

	
<script src="${pageContext.request.contextPath}/lib/fullcalendar/fullcalendar.min.js"></script>
<script>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		var calendar = $('#calendarmanager').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
			},
			editable: true,
			events: [
				/*{
					title: 'All Day Event',
					start: new Date(y, m, 1)
				},
				{
					title: 'Long Event',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-2)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+4, 16, 0),
					allDay: false
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false
				},
				{
					title: 'Click for Google',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/'
				}*/
			]
		});
		
	});

</script>
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}

	#calendarmanager {
		width: 900px;
		margin: 0 auto;
		}

</style>
</head>

<body>
	<!-- AppContent starts which is main container of template -->

	<div id="appContent">
		<!-- Header starts -->
		<header id="Header">
			<div class="container">
				<div class="row">
					<div class="span4">
						<!-- Logo and site link -->
						<div class="logo">
							<h1>AmsaaS</h1>
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- Header ends -->

		<!-- Navigation Starts -->
		<div id="Main_Menu" class="navbar">
			<div class="navbar-inner">
				<div class="tabbable">
					<div class="container">
						<div class="row">
							<div class="span12">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#people" data-toggle="tab"><i
											class="icon-asterisk"></i> PEOPLE</a></li>
									<li><a href="#finances" data-toggle="tab"><i
											class="icon-briefcase"></i> FINANCES</a></li>

									<li><a href="#billpayments" data-toggle="tab"><i
											class="icon-list-alt"></i> BILLING &amp; PAYMENTS</a></li>
									<li><a href="#servicemanager" data-toggle="tab"><i
											class="icon-asterisk"></i> APARTMENT SERVICES</a></li>
									<li><a href="#resourcemanager" data-toggle="tab"><i
											class="icon-calendar"></i> BOOKING CALENDAR</a></li>											
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Navigation Ends -->

		<!-- Content starts -->
		<div id="ContentBody" class="container well">
			<div class="row">
				<div class="span12">
					<div class="tab-content">
						<div class="tab-pane active" id="people"></div>
						<div class="tab-pane" id="finances"></div>
						<div class="tab-pane" id="billpayments">
							<div id="billNPaymentManagerTabs" class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#billDashBoardDiv" data-toggle="tab">BILLS DASHBOARD</a></li>
									<li><a href="#billCreateDiv" data-toggle="tab">CREATE BILL </a></li>
									<li><a href="#billPaymentsDiv" data-toggle="tab">PAYMENTS DASHBOARD</a></li>
								</ul>
								<div class="tab-content">
									<div id="billDashBoardDiv" class="tab-pane active"></div>
									<div id="billPaymentsDiv" class="tab-pane">PAYMENTS</div>
									<div id="billCreateDiv" class="tab-pane"></div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="servicemanager"></div>
						<div class="tab-pane" id="resourcemanager"></div>
					</div>
				</div>
			</div>
		</div>
	<!-- Content Ends -->

			<!-- Footer Starts-->
			<footer id="Footer">
				<div class="container">
					<div class="row">
						<div class="span4">
							<div>
								<h4>About Us</h4>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									Cras elementum dolor eget nisi fermentum quis hendrerit magna
									vestibulum.</p>
								<ul>
									<li><a href="#">Sed eu leo orci, condimentum gravida
											metus <i class="sprite-glyphicons_001_leaf"> </i>
									</a></li>
									<li><a href="#">Etiam at nulla ipsum, in rhoncus purus</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="span4">
							<div>
								<h4>Recent Posts</h4>
								<ul>
									<li><a href="#">Sed eu leo orci, in rhoncus puru
											condimentum gravida metus</a></li>
									<li><a href="#">Etiam at in rhoncus puru nulla ipsum,
											in rhoncus purus</a></li>
									<li><a href="#">Fusce vel magnain rhoncus puru
											faucibus felis dapibus facilisis</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</footer>
			<!-- Footer Ends-->
		</div>
		<!-- Appcontent Ends-->
</body>
</html>