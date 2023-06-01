<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.*"%>
<%@page import="java.util.*"%>
<%@page import="model.Utils"%>
<%@page import="model.dto.TicketInfo"%>
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
<link href="/projectTwo/dashboard/css/styles.css" rel="stylesheet" />
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
	<%@ include file="header/NavBarHeader.jsp"%>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">台灣高鐵時刻表與票價查詢</h1>
			<hr>
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i>
				</div>
				<div class="card-body">
					<article class="text-center container" style="height: 2000px">
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
				</div>
			</div>
		</div>
	</main>
	<%@ include file="footer/NavBarFooter.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="/projectTwo/dashboard/js/scripts.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
		crossorigin="anonymous"></script>
	<script src="/projectTwo/dashboard/js/datatables-simple-demo.js"></script>
	<script src="https://kit.fontawesome.com/0a9f356c66.js"
		crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/bda18b13c5.js"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
	var basePath = "<%=basePath%>";

		$(document).ready(function() {
			document.querySelector("#queryResult").hidden = true;
		});

		function show() {
			let table;
			let allTicketInfoDataSource = basePath
					+ "services/GetAllTicketInfo";
			if ($.fn.dataTable.isDataTable('#queryResult')) {
				table = $('#queryResult').DataTable();
			} else {
				table = $('#queryResult').DataTable({
					ajax : allTicketInfoDataSource
				});
			}
			table.ajax.reload();
			document.querySelector("#queryResult").hidden = false;
		}

		function insertRecord() {
			location.href = basePath + "highSpeedRail/insert";
		}

		function updateTarget(id) {
			location.href = basePath + "highSpeedRail/update?id=" + id;
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
			xhttp.open("POST", basePath + "services/DeleteTicketInfo");
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send("services=DeleteTicketInfo&id=" + id);
		}
	</script>
</body>

</html>