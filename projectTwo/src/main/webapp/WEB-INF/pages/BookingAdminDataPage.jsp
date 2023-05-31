<%@page import="model.dto.PriceInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">


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


%>

<body onload="showPrice()" class="sb-nav-fixed">

	<%@ include file="header/NavBarHeader.jsp"%>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">Dashboard</h1>

			<hr>
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i> DataTable Example
				</div>
				<div class="card-body">
					<article class="container">
						<div class="aside">
							<div class="section">
								<form method='post'
									action='<spring:url value="/highSpeedRail/doAction"/>'>
									<h2 style="text-align: center;">增修資料</h2>
									<div style="margin: 5px;">
										<label for="ticketID" class="t1">訂票編號：</label> <input
											type="text" id="ticketID" name="ticketID"
											value="${ticketDto.ticketID}" class="form-control"
											${ticketDto==null ? "autofocus" : "onclick='blur()'"}>
									</div>
									<div style="margin: 5px;">
										<label for="tranNo" class="t1">班次：</label> <input type="text"
											id="tranNo" name="tranNo" value="${ticketDto.tranNo}"
											class="form-control" autofocus>
									</div>
									<div style="margin: 5px;">
										<label for="seat" class="t1">座位：</label> <input type="text"
											id="seat" name="seat" value="${ticketDto.seat}"
											class="form-control" autofocus>
									</div>
									<div style="margin: 5px;">
										<label for="DepartureST" class="t1">出發站：</label>
										<!-- 改成用selection(下拉是選單) ↓ -->
										<select id="DepartureST" name="departureST"
											class="form-control mt-0 select-type01" title="出發站"
											onchange="showPrice()">
											<%-- <%
													List<StationInfoDTO> stationList = (ArrayList<StationInfoDTO>) request.getAttribute("stationList");
													for (StationInfoDTO st : stationList) {
														String selected = departureST.equals(Integer.toString(st.getStationID())) ? "selected" : "";
													%> --%>
											<c:forEach var="station" items="${stationList}">
												<option value="${station.stationID}"
													${ticketDto.departureST.equals(Integer.toString(st.getStationID())) ? "selected" : ""}>
													${station.stationName}</option>
											</c:forEach>
											<%-- <%
													}
													%> --%>
										</select>
									</div>
									<div style="margin: 5px;">
										<label for="DestinationST" class="t1">抵達站：</label>
										<!-- 改成用selection(下拉是選單) ↓ -->
										<select id="DestinationST" name="destinationST"
											class="form-control mt-0 select-type01" title="到達站"
											onchange="showPrice()">
											<%-- <%
													for (StationInfoDTO st : stationList) {
														String selected = destinationST.equals(Integer.toString(st.getStationID())) ? "selected" : "";
													%> --%>
											<c:forEach var="station" items="${stationList}">
												<option value="${station.stationID}"
													${ticketDto.destinationST.equals(Integer.toString(st.getStationID())) ? "selected" : ""}>
													${station.stationName}</option>
											</c:forEach>
											<%-- <%
													}
													%> --%>
										</select>
									</div>
									<div style="margin: 5px;">
										<label for="depturedate" class="t1">出發日期：</label> <input
											type="date" id="departuredate" name="departuredate"
											value="${ticketDto.departuredate}" class="form-control"
											autofocus>
									</div>
									<div style="margin: 5px;">
										<label for="departuretime" class="t1">出發時間：</label> <input
											type="text" id="departuretime" name="departuretime"
											value="${ticketDto.departuretime}" class="form-control"
											autofocus>
									</div>
									<div style="margin: 5px;">
										<label for="arrivaltime" class="t1"> 抵達時間： </label> <input
											type="text" id="arrivaltime" name="arrivaltime"
											value="${ticketDto.arrivaltime}" class="form-control"
											autofocus />
									</div>
									<div style="margin: 5px;">
										<label for="price" class="t1">票價：</label> <input type="text"
											id="price" name="price" value="${ticketDto.price}"
											class="form-control mt-0 select-type01">
									</div>
									<div style="margin: 5px;">
										<label for="bookingdate" class="t1">訂票日期：</label> <input
											type="date" id="bookingdate" name="bookingdate"
											value="${ticketDto.bookingdate}" class="form-control"
											autofocus>
									</div>
									<div style="text-align: center; padding: 30px;">
										<button type="submit" class="btn btn-dark"
											style="margin: 10px;">${ticketDto== null ? "新增" : "修改"}</button>
										<button type="reset" onclick="history.back()"
											class="btn btn-dark" style="margin: 10px;">取消</button>
									</div>
									<input type="hidden" name="action"
										value='${ticketDto== null ? "doInsert" : "doUpdate"}' />
								</form>
							</div>
						</div>
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
	<script type="text/javascript">
		var priceInfos = new Map();
		let tmpS;
		<c:forEach var="pk" items="${priceInfos.entrySet()}">
		tmpS = new Set();
		tmpS.add(${pk.getKey().toArray()[0]});
		tmpS.add(${pk.getKey().toArray()[1]});
		priceInfos.set(tmpS, ${pk.getValue()})
		</c:forEach>
		function showPrice() {
			let departureST = document.querySelector("#DepartureST");
			let destinationST = document.querySelector("#DestinationST");
			let price = document.querySelector("#price");
			if (parseInt(departureST.value) == parseInt(destinationST.value)) {
				price.value = 0;
				return;
			}
			priceInfos.forEach((value, key, map) => {
				if (key.has(parseInt(departureST.value)) && key.has(parseInt(destinationST.value))) {
					price.value = value;
					return;
				}
			});
		}
	</script>
</body>

</html>