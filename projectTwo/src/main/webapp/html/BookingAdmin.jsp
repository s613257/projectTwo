<%@page import="model.dto.TicketDTO"%>
<%@page import="model.dto.PriceInfoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.*"%>
<%@page import="java.util.*"%>
<%@page import="model.dto.StationInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<title>SelectPage</title>
<style>
.Traveltime {
	font-size: small;
}

td {
	font-size: large;
}
</style>
</head>

<%
Map<Set<String>, Integer> priceInfos = (Map<Set<String>, Integer>) request.getAttribute("priceInfos");
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
%>


<body onload="showPrice()">


	<form method="POST" action="">
		<header
			class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
			<div class="container-fluid d-flex align-items-center">
				<h1 class="d-flex align-items-center fs-4 text-white mb-0">
					Teavel Master</h1>
				<a href="/docs/5.0/examples/cheatsheet-rtl/"
					class="ms-auto link-light" hreflang="ar">RTL cheatsheet</a>
			</div>
		</header>
		<article class="py-5 text-center container">
			<div>
				<h3>台灣高鐵時刻表與票價查詢</h3>
			</div>
			<hr>
			<section class="timetable-search-frame bg-grey">
				<div class="container">
					<div class="download-ttable"></div>
					<div class="ha-nav-content">
						<div class="p-3 mb-2 bg-light border border-2 rounded">
							<div class="inline-input-group row">
								<div class="col-input col">
									<button type="button" title="查詢訂票資訊"
										class="btn btn-secondary  mt-3" id="search" onclick="search()">查詢訂票資訊</button>
								</div>
								<div class="col-input col">
									<button type="button" title="新增單筆資料"
										class="btn btn-secondary  mt-3" id="insert">新增單筆資料</button>
								</div>
							</div>
						</div>
					</div>
					<div>
						<hr>
						<table id="queryResult"
							class="table table-bordered table-striped table-sm">
							<thead class="table-light">
								<tr>
									<th scope="col" class="align-middle">訂票編號</th>
									<th scope="col" class="align-middle">班次</th>
									<th scope="col" class="align-middle">座位</th>
									<th scope="col" class="align-middle">出發站</th>
									<th scope="col" class="align-middle">抵達站</th>
									<th scope="col" class="align-middle">出發時間</th>
									<th scope="col" class="align-middle">抵達時間</th>
									<th scope="col" class="align-middle">票價</th>
									<th scope="col" class="align-middle">訂票日期</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%
								List<TicketDTO> tk = (ArrayList<TicketDTO>) request.getAttribute("tk");
								for (TicketDTO tk_dto : tk) {
								%>
								<tr>
									<td><%=tk_dto.getTicketID()%></td>
									<td><%=tk_dto.getTranNo()%></td>
									<td><%=tk_dto.getSeat()%></td>
									<td><%=tk_dto.getDeparture_ST()%></td>
									<td><%=tk_dto.getDestination_ST()%></td>
									<td><%=tk_dto.getDeparture_time()%></td>
									<td><%=tk_dto.getArrival_time()%></td>
									<td><%=tk_dto.getPrice()%></td>
									<td><%=tk_dto.getDate()%></td>
									<td><input type="button"
										onclick="formSubmit(<%=tk_dto.getTicketID()%>)" value="修改">
									</td>
									<td><input type="button"
										onclick="formSubmit(<%=tk_dto.getTicketID()%>)" value="刪除">
									</td>
									<%
									}
									%>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</article>
		<input type="hidden" name="action" value="" />
	</form>


	<script type="text/javascript">
	function search(){
		let department_ST = document.querySelector("#Department_ST");
		let department_ST = document.querySelector("#Department_ST");
		let department_ST = document.querySelector("#Department_ST");
		let department_ST = document.querySelector("#Department_ST");
		let department_ST = document.querySelector("#Department_ST");
		let destination_ST = document.querySelector("#Destination_ST");
		let typesofticket = document.querySelector("#typesofticket");
		let departdate03 = document.querySelector("#Departdate03");
		let departTime03 = document.querySelector("#DepartTime03");
		if(parseInt(department_ST.value) == parseInt(destination_ST.value)){
			alert("起程站 與 到達站 相同")
			return;
		}
		if(departdate03.value == ""){
			alert("請選擇出發日期")
			return;
		}
		if(departTime03.value == ""){
			alert("請選擇出發時刻")
			return;
		}
		
		let queryResult = document.querySelector("#queryResult");
		
		 const xhttp = new XMLHttpRequest();
         let data;
         const paramsObject = {};
         paramsObject.department_ST = department_ST.value;
         paramsObject.destination_ST = destination_ST.value;
         paramsObject.typesofticket = typesofticket.value;
         paramsObject.departdate03 = departdate03.value;
         paramsObject.departTime03 = departTime03.value;
         
         const params = JSON.stringify(paramsObject);

         xhttp.onload = function () {
             if (this.status === 200) {
/*                  tranInfos = JSON.parse(this.response);
                 console.log(tranInfos); */
                 
             }
         }
         xhttp.open("POST", "<%=basePath%>WebServices");
         xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
         xhttp.send("params=" + params);
         
         
		queryResult.style.display="";
	}
	
</script>
</body>

</html>