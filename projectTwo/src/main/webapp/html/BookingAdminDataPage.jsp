<%@page import="java.util.*"%>
<%@page import="java.util.Map.*"%>
<%@page import="model.dto.TicketDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.PriceInfoDTO"%>
<%@page import="model.dto.StationInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


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
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
<title>SelectPage</title>
<style>
.container {
	width: 500px;
	height: 500px;
}

.aside {
	border: 2px solid rgb(215, 211, 211);
	border-radius: 30px;
	background-color: #FFF;
	box-shadow: 3px 3px 3px gray;
	margin: 10px 0px 0px 0px;
}

.section {
	border-radius: 5px;
	padding: 40px 40px 5px 40px;
	line-height: 2em;
}
</style>
</head>

<%
Map<Set<String>, Integer> priceInfos = (Map<Set<String>, Integer>) request.getAttribute("priceInfos");
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";

TicketDTO tkdto = (TicketDTO) request.getAttribute("tkdto");
String btn_title = tkdto == null ? "新增" : "修改";
String TicketID = tkdto == null ? "" : Integer.toString(tkdto.getTicketID());
String TranNo = tkdto == null ? "" : tkdto.getTranNo();
String Seat = tkdto == null ? "" : tkdto.getSeat();
String Departure_ST = tkdto == null ? "" : tkdto.getDeparture_ST();
String Destination_ST = tkdto == null ? "" : tkdto.getDestination_ST();
String Departure_time = tkdto == null ? "" : tkdto.getDeparture_time();
String Arrival_time = tkdto == null ? "" : tkdto.getArrival_time();
String price = tkdto == null ? "" : Integer.toString(tkdto.getPrice());
String date = tkdto == null ? "" : tkdto.getDate();
%>

<body onload="showPrice()">
	<header
		class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
		<div class="container-fluid d-flex align-items-center">
			<h1 class="d-flex align-items-center fs-4 text-white mb-0">
				Teavel Master</h1>
			<a href="/docs/5.0/examples/cheatsheet-rtl/"
				class="ms-auto link-light" hreflang="ar">RTL cheatsheet</a>
		</div>
	</header>
	<article class="container">
		<div class="aside">
			<div class="section">
				<form method="post" action="BookingadminController">
					<h2 style="text-align: center;">增修資料</h2>
					<div style="margin: 5px;">
						<label for="" class="t1">訂票編號：</label> <input type="text" id=""
							name="TicketID" value="<%=TicketID%>" class="form-control"
							<%=TicketID.isEmpty() ? "autofocus" : "onclick='blur()'"%>>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">班次：</label> <input type="text" id=""
							name="TranNo" value="<%=TranNo%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">座位：</label> <input type="text" id=""
							name="Seat" value="<%=Seat%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">出發站：</label>
						<!-- 改成用selection(下拉是選單) ↓ -->
						<select id="Departure_ST" name="Departure_ST"
							class="form-control mt-0 select-type01" title="出發站"
							onchange="showPrice()">
							<%
							List<StationInfoDTO> stationList = (ArrayList<StationInfoDTO>) request.getAttribute("stationList");
							for (StationInfoDTO st : stationList) {
								String selected = Departure_ST.equals(Integer.toString(st.getStationID())) ? "selected" : "";
							%>
							<option value="<%=st.getStationID()%>" <%=selected%>>
								<%=st.getStationName()%>
							</option>
							<%
							}
							%>
						</select>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">抵達站：</label>
						<!-- 改成用selection(下拉是選單) ↓ -->
						<select id="Destination_ST" name="Destination_ST"
							class="form-control mt-0 select-type01" title="到達站"
							onchange="showPrice()">
							<%
							for (StationInfoDTO st : stationList) {
								String selected = Destination_ST.equals(Integer.toString(st.getStationID())) ? "selected" : "";
							%>
							<option value="<%=st.getStationID()%>" <%=selected%>>
								<%=st.getStationName()%>
							</option>
							<%
							}
							%>
						</select>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">出發時間：</label> <input type="text" id=""
							name="Departure_time" value="<%=Departure_time%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1"> 抵達時間： </label> <input type="text" id=""
							name="Arrival_time" value="<%=Arrival_time%>" class="form-control" autofocus />
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">票價：</label> <input type="text" id="price"
							name="price" value="<%=price%>" class="form-control mt-0 select-type01">
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">訂票日期：</label> <input type="date" id=""
							name="Date" value="<%=date%>" class="form-control" autofocus>
					</div>
					<div style="text-align: center; padding: 30px;">
						<button type="submit" class="btn btn-dark" style="margin: 10px;"><%=btn_title%></button>
						<button type="reset"  onclick="history.back()" class="btn btn-dark" style="margin: 10px;">取消</button>
					</div>
					<input type="hidden" name="action" value="<%=tkdto == null ? "doInsert" : "doUpdate" %>" />
				</form>
			</div>
		</div>
	</article>

	<script type="text/javascript">
		var priceInfos = new Map();
		let tmpS;
		<%for (Entry<Set<String>, Integer> pk : priceInfos.entrySet()) {%>
		tmpS = new Set();
		tmpS.add(<%=pk.getKey().toArray()[0]%>);
		tmpS.add(<%=pk.getKey().toArray()[1]%>);
		priceInfos.set(tmpS, <%=pk.getValue()%>)
		<%}%>
		function showPrice() {
			let departure_ST = document.querySelector("#Departure_ST");
			let destination_ST = document.querySelector("#Destination_ST");
			let price = document.querySelector("#price");
			if (parseInt(departure_ST.value) == parseInt(destination_ST.value)) {
				price.value = 0;
				return;
			}
			priceInfos.forEach((value, key, map) => {
				if (key.has(parseInt(departure_ST.value)) && key.has(parseInt(destination_ST.value))) {
					price.value = value;
					return;
				}
			});
		}
	</script>
</body>

</html>