<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header/PageHeader.jsp"%>
<!-- ↓↓↓↓↓↓↓ head field ↓↓↓↓↓↓↓  -->
<%@ include file="header/DataTableFiles.jsp"%>
<!-- ↑↑↑↑↑↑↑ head field ↑↑↑↑↑↑↑  -->
</head> <!-- <= 不能省略 補足PageHeader.jsp裡面的<head>  -->

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
					<article class="text-center container">
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