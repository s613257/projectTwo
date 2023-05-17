<%@page import="model.dto.TicketDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.*"%>
<%@page import="java.util.*"%>
<%@page import="model.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="/ProjectTripMaster/TM.sean/dashboard/css/styles.css"
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
<script
	src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<link
	href="https://cdn.datatables.net/v/dt/dt-1.13.4/datatables.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.datatables.net/v/dt/dt-1.13.4/datatables.min.js"></script>

<title>SelectPage</title>
</head>

<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
%>

<body>

	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="index.jsp">Travel Master</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">

		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="#!">Settings</a></li>
					<li><a class="dropdown-item" href="#!">Activity Log</a></li>
					<li>
						<hr class="dropdown-divider" />
					</li>
					<li><a class="dropdown-item" href="#!">Logout</a></li>
				</ul></li>
		</ul>
	</nav>

	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">後台管理者</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts" aria-expanded="false"
							aria-controls="collapseLayouts">
							<div class="sb-nav-link-icon">
								<i class="fa-regular fa-user"></i>
							</div> 使用者
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="layout-static.jsp">Static
									Navigation</a> <a class="nav-link" href="layout-sidenav-light.jsp">Light
									Sidenav</a> <a class="nav-link" href="login.jsp">Login</a> <a
									class="nav-link" href="register.jsp">Register</a> <a
									class="nav-link" href="password.jsp">Forgot Password</a>
							</nav>
						</div>
						<a class="nav-link" href="index.jsp">
							<div class="sb-nav-link-icon">
								<i class="fa-solid fa-location-dot"></i>
							</div> 行程
						</a> <a class="nav-link" href="index.jsp">
							<div class="sb-nav-link-icon">
								<i class="fa-solid fa-ticket"></i>
							</div> 訂票
						</a> <a class="nav-link" href="index.jsp">
							<div class="sb-nav-link-icon">
								<i class="fa-regular fa-comment-dots"></i>
							</div> 論壇
						</a> <a class="nav-link" href="index.jsp">
							<div class="sb-nav-link-icon">
								<i class="fa-solid fa-users"></i>
							</div> 陪玩
						</a> <a class="nav-link" href="index.jsp">
							<div class="sb-nav-link-icon">
								<i class="fa-solid fa-cart-shopping"></i>
							</div> 訂單管理
						</a>
					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Version : 1.0.0</div>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">台灣高鐵時刻表與票價查詢</h1>
					<hr>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> DataTable Example
						</div>
						<div class="card-body">
							<button type="button"
								class="btn btn-secondary btn-floating btn-lg"
								id="btn-back-to-top">
								<i class="fas fa-arrow-up"></i>
							</button>
							<article class="py-5 text-center container"
								style="height: 2000px">
								<section class="timetable-search-frame bg-grey">
									<div class="container">
										<div class="download-ttable"></div>
										<div class="ha-nav-content">
											<div class="p-3 mb-2 bg-light border border-2 rounded">
												<div class="inline-input-group row">
													<div class="col-input col">
														<button type="button" title="查詢訂票資訊"
															class="btn btn-secondary  mt-3" id="search"
															onclick="show()">查詢訂票資訊</button>
													</div>
													<div class="col-input col">
														<button type="button" title="新增單筆資料"
															class="btn btn-secondary  mt-3" id="insert"
															onclick="insertRecord()">新增單筆資料</button>
													</div>
												</div>
											</div>
										</div>
										<div>
											<hr>
											<table id="queryResult"
												class="table table-bordered table-striped table-sm display"
												style="width: 100%;">
												<thead class="table-light">
													<tr>
														<th scope="col" class="align-middle">#</th>
														<th scope="col" class="align-middle">班次</th>
														<th scope="col" class="align-middle">座位</th>
														<th scope="col" class="align-middle">出發站</th>
														<th scope="col" class="align-middle">抵達站</th>
														<th scope="col" class="align-middle">出發日期</th>
														<th scope="col" class="align-middle">出發時間</th>
														<th scope="col" class="align-middle">抵達時間</th>
														<th scope="col" class="align-middle">票價</th>
														<th scope="col" class="align-middle">訂票日期</th>
														<th></th>
														<th></th>
													</tr>
												</thead>
												<tbody id="queryContent">
												</tbody>
											</table>
										</div>
									</div>

								</section>
							</article>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div>
							<!--請勿更動-->
						</div>
						<div class="text-muted">Copyright &copy; Travel Master 2023</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="/ProjectTripMaster/TM.sean/dashboard/js/scripts.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
		crossorigin="anonymous"></script>
	<script
		src="/ProjectTripMaster/TM.sean/dashboard/js/datatables-simple-demo.js"></script>
	<script src="https://kit.fontawesome.com/0a9f356c66.js"
		crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/bda18b13c5.js"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var action_update = "<%=Utils.ACTION_UPDATE%>";
	
	$(document).ready(function () {
		document.querySelector("#queryResult").hidden = true;
	});
	
		function show() {
			  
			/* let queryResult = document.querySelector("#queryResult");

			const xhttp = new XMLHttpRequest();
			xhttp.onload = function () {
				if (this.status === 200) {
					ticketInfos = JSON.parse(this.response);
					console.log(ticketInfos);
				 placeQueryContent(ticketInfos); 

					
				}
			}
			xhttp.open("POST",  basePath + "WebServices");
			xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
			xhttp.send("service=GetAllTicketInfo"); */
			let table;
			let allTicketInfoDataSource = basePath + "WebServices?service=GetAllTicketInfo";
			if ( $.fn.dataTable.isDataTable( '#queryResult' ) ) {
			    table = $('#queryResult').DataTable();
			}
			else {
			    table = $('#queryResult').DataTable( {
			    	ajax: allTicketInfoDataSource
			    } );
			}
			table.ajax.reload();
			document.querySelector("#queryResult").hidden = false;
			//queryResult.style.display = "";
		}

		function placeQueryContent(ticketInfos) {
			let queryContent = document.querySelector("#queryContent");
			queryContent.innerHTML = '';
			ticketInfos.forEach(function (ticketInfo) {
				let infoRow = document.createElement("tr");
				let id = document.createElement("td");
				id.innerHTML = ticketInfo.ticketID;
				let tranNo = document.createElement("td");
				tranNo.innerHTML = ticketInfo.tranNo;
				let seat = document.createElement("td");
				seat.innerHTML = ticketInfo.seat;
				let dep_st = document.createElement("td");
				dep_st.innerHTML = ticketInfo.departure_ST;
				let des_st = document.createElement("td");
				des_st.innerHTML = ticketInfo.destination_ST;
				let dep_date = document.createElement("td");
				dep_date.innerHTML = ticketInfo.departure_date;
				let dep_time = document.createElement("td");
				dep_time.innerHTML = ticketInfo.departure_time;
				let arr_time = document.createElement("td");
				arr_time.innerHTML = ticketInfo.arrival_time;
				let price = document.createElement("td");
				price.innerHTML = ticketInfo.price;
				let date = document.createElement("td");
				date.innerHTML = ticketInfo.booking_date;

				let updt = document.createElement("td");
				let updt_btn = document.createElement("button");
				let updt_icon = document.createElement("i");
				updt_icon.className = "fa-solid fa-pen-to-square";
				updt_btn.appendChild(updt_icon);
				updt.appendChild(updt_btn);
				updt_btn.classList.add("btn");
				updt_btn.classList.add("btn-light");
				updt_btn.onclick = function () { updateTarget(ticketInfo.ticketID); };

				let del = document.createElement("td");
				let del_btn = document.createElement("button");
				let del_icon = document.createElement("i");
				del_icon.className = "fa-solid fa-trash-can";
				del_btn.appendChild(del_icon);
				del.appendChild(del_btn);
				del_btn.classList.add("btn");
				del_btn.classList.add("btn-light");
				del_btn.onclick = function () { deleteTarget(ticketInfo.ticketID) };

				infoRow.appendChild(id);
				infoRow.appendChild(tranNo);
				infoRow.appendChild(seat);
				infoRow.appendChild(dep_st);
				infoRow.appendChild(des_st);
				infoRow.appendChild(dep_date);
				infoRow.appendChild(dep_time);
				infoRow.appendChild(arr_time);
				infoRow.appendChild(price);
				infoRow.appendChild(date);
				infoRow.appendChild(updt);
				infoRow.appendChild(del);

				queryContent.appendChild(infoRow);
			});
		}
		
		function insertRecord() {
			location.href = basePath + "BookingadminController?action=<%=Utils.ACTION_INSERT%>";
		}

		function updateTarget(id) {
			location.href = basePath + "BookingadminController?action="
					+ action_update + "&id=" + id;
		}

		function deleteTarget(id) {
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				if (this.status === 200) {
					deleteResult = JSON.parse(this.response);
					alert(deleteResult.msg);
					document.querySelector("#search").click();
				}
			}
			xhttp.open("POST", basePath + "WebServices");
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send("service=DeleteTicketInfo&id=" + id);
		}

		// GO TO TOP
		let mybutton = document.getElementById("btn-back-to-top");

		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {
			scrollFunction();
		};

		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
				mybutton.style.display = "block";
			} else {
				mybutton.style.display = "none";
			}
		}
		// When the user clicks on the button, scroll to the top of the document
		mybutton.addEventListener("click", backToTop);

		function backToTop() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>
</body>

</html>