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
<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/projectTwo/dashboard/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
String ticketID = tkdto == null ? "" : Integer.toString(tkdto.getTicketID());
String tranNo = tkdto == null ? "" : tkdto.getTranNo();
String seat = tkdto == null ? "" : tkdto.getSeat();
String departureST = tkdto == null ? "" : tkdto.getDepartureST();
String destinationST = tkdto == null ? "" : tkdto.getDestinationST();
String depturedate = tkdto == null ? "" : tkdto.getDeparturedate();
String departuretime = tkdto == null ? "" : tkdto.getDeparturetime();
String arrivaltime = tkdto == null ? "" : tkdto.getArrivaltime();
String price = tkdto == null ? "" : Integer.toString(tkdto.getPrice());
String bookingdate = tkdto == null ? "" : tkdto.getBookingdate();
%>

<body onload="showPrice()"  class="sb-nav-fixed">
	 <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Navbar Brand-->
        <a class="navbar-brand ps-3" href="index.jsp">Travel Master</a>
        <!-- Sidebar Toggle-->
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
                class="fas fa-bars"></i></button>
        <!-- Navbar Search-->
        <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">

        </form>
        <!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="#!">Settings</a></li>
                    <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                    <li>
                        <hr class="dropdown-divider" />
                    </li>
                    <li><a class="dropdown-item" href="#!">Logout</a></li>
                </ul>
            </li>
        </ul>
    </nav>

    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <div class="sb-sidenav-menu-heading">後台管理者</div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fa-regular fa-user"></i></div>
                            使用者
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="layout-static.jsp">Static Navigation</a>
                                <a class="nav-link" href="layout-sidenav-light.jsp">Light Sidenav</a>
                                <a class="nav-link" href="login.jsp">Login</a>
                                <a class="nav-link" href="register.jsp">Register</a>
                                <a class="nav-link" href="password.jsp">Forgot Password</a>
                            </nav>
                        </div>
                        <a class="nav-link" href="index.jsp">
                            <div class="sb-nav-link-icon"><i class="fa-solid fa-location-dot"></i></div>
                            行程
                        </a>
                        <a class="nav-link" href="index.jsp">
                            <div class="sb-nav-link-icon"><i class="fa-solid fa-ticket"></i></div>
                            訂票
                        </a> <a class="nav-link" href="index.jsp">
                            <div class="sb-nav-link-icon"><i class="fa-regular fa-comment-dots"></i></div>
                            論壇
                        </a>
                        <a class="nav-link" href="index.jsp">
                            <div class="sb-nav-link-icon"><i class="fa-solid fa-users"></i></div>
                            陪玩
                        </a>
                        <a class="nav-link" href="index.jsp">
                            <div class="sb-nav-link-icon"><i class="fa-solid fa-cart-shopping"></i></div>
                            訂單管理
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
                    <h1 class="mt-4">Dashboard</h1>
                    <hr>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            DataTable Example
                        </div>
                        <div class="card-body">
	<article class="container">
		<div class="aside">
			<div class="section">
				<form method="post" action="BookingadminController">
					<h2 style="text-align: center;">增修資料</h2>
					<div style="margin: 5px;">
						<label for="" class="t1">訂票編號：</label> <input type="text" id=""
							name="ticketID" value="<%=ticketID%>" class="form-control"
							<%=ticketID.isEmpty() ? "autofocus" : "onclick='blur()'"%>>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">班次：</label> <input type="text" id=""
							name="tranNo" value="<%=tranNo%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">座位：</label> <input type="text" id=""
							name="seat" value="<%=seat%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">出發站：</label>
						<!-- 改成用selection(下拉是選單) ↓ -->
						<select id="DepartureST" name="departureST"
							class="form-control mt-0 select-type01" title="出發站"
							onchange="showPrice()">
							<%
							List<StationInfoDTO> stationList = (ArrayList<StationInfoDTO>) request.getAttribute("stationList");
							for (StationInfoDTO st : stationList) {
								String selected = departureST.equals(Integer.toString(st.getStationID())) ? "selected" : "";
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
						<select id="DestinationST" name="destinationST"
							class="form-control mt-0 select-type01" title="到達站"
							onchange="showPrice()">
							<%
							for (StationInfoDTO st : stationList) {
								String selected = destinationST.equals(Integer.toString(st.getStationID())) ? "selected" : "";
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
						<label for="" class="t1">出發日期：</label> <input type="date" id=""
							name="depturedate" value="<%=depturedate%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">出發時間：</label> <input type="text" id=""
							name="departuretime" value="<%=departuretime%>" class="form-control" autofocus>
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1"> 抵達時間： </label> <input type="text" id=""
							name="arrivaltime" value="<%=arrivaltime%>" class="form-control" autofocus />
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">票價：</label> <input type="text" id="price"
							name="price" value="<%=price%>" class="form-control mt-0 select-type01">
					</div>
					<div style="margin: 5px;">
						<label for="" class="t1">訂票日期：</label> <input type="date" id=""
							name="bookingdate" value="<%=bookingdate%>" class="form-control" autofocus>
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
</main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div>
                            <!--請勿更動-->
                        </div>
                        <div class="text-muted">Copyright &copy; Travel Master 2023</div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/projectTwo/dashboard/js/scripts.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
    <script src="/projectTwo/dashboard/js/datatables-simple-demo.js"></script>
    <script src="https://kit.fontawesome.com/0a9f356c66.js" crossorigin="anonymous"></script>
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