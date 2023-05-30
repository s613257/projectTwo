<%@page import="model.dto.PriceInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.*"%>
<%@page import="java.util.*"%>
<%@page import="model.dto.StationInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<title>Travel Master</title>

<!-- CSS FILES -->
<link rel="preconnect" href="https://fonts.googleapis.com">

<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link
	href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&display=swap"
	rel="stylesheet">

<link href="/projectTwo/front/css/bootstrap.min.css" rel="stylesheet">

<link href="/projectTwo/front/css/bootstrap-icons.css" rel="stylesheet">

<link href="/projectTwo/front/css/templatemo-leadership-event.css"
	rel="stylesheet">
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
</head>

<%
Map<Set<String>, Integer> priceInfos = (Map<Set<String>, Integer>) request.getAttribute("priceInfos");
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
%>


<body onload="showPrice()">


	<nav class="navbar navbar-expand-lg">
		<div class="container">

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<a href="index.html" class="navbar-brand mx-auto mx-lg-0"> <i
				class="fa-solid fa-martini-glass-citrus"></i> <span
				class="brand-text">TM</span>
			</a> <a class="nav-link custom-btn btn d-lg-none" href="#">Loggin</a>

			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_1"> <i class="fa-solid fa-house"></i></a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_2"> About</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_3"> <i class="fa-solid fa-location-dot"></i> 景點
					</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_4"> <i class="fa-solid fa-calendar-days"></i>
							行程
					</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_5"> <i class="fa-solid fa-ticket"> </i> 訂票
					</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_6"> <i class="fa-solid fa-face-smile-wink">
						</i> 陪玩
					</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_9"> <i class="fa-solid fa-comments"> </i> 論壇
					</a></li>

					<li class="nav-item"><a class="nav-link"
						href="/projectTwo/front/ShoppingCart.jsp"><i
							class="fa-solid fa-cart-shopping"></i> 購物車</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="#section_8"><i class="fa-solid fa-phone"></i> 聯繫</a></li>



					<ul style="list-style: none;">
					  <li id="profile"><a class="nav-link custom-btn btn d-none d-lg-block" >Hi,</a></li>
					  <li class="nav-item"><a class="nav-link click-scroll" id="goToBackstage">後台管理系統</a></li>
					  <li class="nav-item"><a class="nav-link click-scroll" id="logOut">登出</a></li>
					</ul>	

				</ul>
			</div>
		</div>
	</nav>
	<main>
		<form>
			<article class="py-5 text-center container">
				<div>
					<h3>台灣高鐵時刻表與票價查詢</h3>
				</div>
				<hr>
				<section class="timetable-search-frame bg-grey">
					<div class="container">
						<div class="p-3 mb-2 bg-light border border-2 rounded">
							<div class="inline-input-group row">
								<div class="col">
									<label class="input-smalllabel" for="select_location01">起程站</label>
									<select id="DepartureST" name="DepartureST"
										class="form-control mt-0 select-type01" title="出發站"
										onchange="showPrice()">
										<%
										List<StationInfo> stationList = (ArrayList<StationInfo>) request.getAttribute("stationList");
																		for (StationInfo st : stationList) {
										%>
										<option value="<%=st.getStationID()%>">
											<%=st.getStationName()%>
										</option>
										<%
										}
										%>
									</select>
								</div>
								<div class="col-input col">
									<label class="input-smalllabel" for="select_location02">到達站</label>
									<select id="DestinationST" name="DestinationST"
										class="form-control mt-0 select-type01" title="到達站"
										onchange="showPrice()">
										<%
										for (StationInfo st : stationList) {
										%>
										<option value="<%=st.getStationID()%>">
											<%=st.getStationName()%>
										</option>
										<%
										}
										%>
									</select>
								</div>

								<div class="col-input col">
									<label class="input-smalllabel" for="typeOfTicket"> 票種
									</label> <select class="form-control mt-0 select-type01" title="票種"
										id="typeOfTicket">
										<option value="tot-1" selected>單趟</option>
										<!-- <option value="tot-2" selected>來回</option> -->
									</select>
								</div>
								<div class="col-input col-2">
									<label class="input-smalllabel" for="departuredate">出發日期</label>
									<input id="departuredate" type="Date"
										class="form_date form-control">
								</div>
								<div class="col-input col-2">
									<label class="input-smalllabel" for="Departuretime">出發時刻</label>
									<input id="Departuretime" type="Time"
										class="form_date form-control">
								</div>
								<div class="col-input col">
									<label class="input-smalllabel" for="typesofticket">
										參考票價 </label> <input type="text" id="price" name="price" value=""
										class="form-control mt-0 select-type01">

								</div>
								<div class="col-input col">
									<button type="button" title="查詢"
										class="btn btn-secondary  mt-3" id="start-search"
										onclick="search()">查詢</button>
								</div>
							</div>
						</div>
					</div>
					<div>
						<hr>
						<div class="container" id="queryResult" style="display: none">
							<div class="row ">
								<div class="col d-flex justify-content-start">
									<div id="from_st" class="d-inline p-2 fs-5"></div>
									<div class="d-inline p-2 fs-5">→</div>
									<div id="to_st" class="d-inline p-2 fs-5"></div>
									<div id="dep_date" class="d-inline p-2 fs-5"></div>
									<!-- TODO  Date -->
								</div>
							</div>
							<div class="row">

								<table
									class="table table-bordered table-striped table-sm table table-striped table-hover">
									<thead class="table-light">
										<tr>
											<th scope="col" class="align-middle">車次</th>
											<th scope="col" class="align-middle">出發時間</th>
											<th scope="col" class="align-middle">抵達時間</th>
											<th scope="col" class="align-middle">搭乘時間</th>
											<!-- 	<th scope="col" class="align-middle">剩餘位數</th> -->
											<th scope="col" class="align-middle">訂票</th>
										</tr>
									</thead>
									<tbody id="queryContent">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</section>
			</article>
			<input type="hidden" name="action" value="" />
		</form>
	</main>
	<script src="/projectTwo/front/js/jquery.min.js"></script>
	<script src="/projectTwo/front/js/bootstrap.min.js"></script>
	<script src="/projectTwo/front/js/jquery.sticky.js"></script>
	<script src="/projectTwo/front/js/click-scroll.js"></script>
	<script src="/projectTwo/front/js/custom.js"></script>
	<script src="https://kit.fontawesome.com/bda18b13c5.js"
		crossorigin="anonymous"></script>
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


										function search() {
											let departure_ST = document.querySelector("#DepartureST");
											let destination_ST = document.querySelector("#DestinationST");
											let typeOfTicket = document.querySelector("#typeOfTicket");
											let departure_date = document.querySelector("#departuredate");
											let departure_time = document.querySelector("#Departuretime");
											if (parseInt(departure_ST.value) == parseInt(destination_ST.value)) {
												alert("起程站 與 到達站 相同")
												return;
											}
											if (departure_date.value == "") {
												alert("請選擇出發日期")
												return;
											}
											if (departure_time.value == "") {
												alert("請選擇出發時刻")
												return;
											}

											let queryResult = document.querySelector("#queryResult");

											const xhttp = new XMLHttpRequest();

											xhttp.onload = function () {
												if (this.status === 200) {
													let from_st = document.querySelector("#from_st");
													let to_st = document.querySelector("#to_st");
													let dep_date = document.querySelector("#dep_date");
													from_st.innerHTML = departure_ST.options[departure_ST.selectedIndex].text;
													to_st.innerHTML = destination_ST.options[destination_ST.selectedIndex].text;
													dep_date.innerHTML = departure_date.value;

													// placeQueryContent(tranInfos);

													tranInfos = JSON.parse(this.response);
													console.log(tranInfos);
													placeQueryContent(tranInfos);

													queryResult.style.display = "";
												}
											}
											xhttp.open("POST", "<%=basePath%>WebServices");
											xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
											xhttp.send("service=GetTranInfo&departure_ST=" + departure_ST.value + "&destination_ST=" + destination_ST.value + "&departure_time=" + departure_time.value);

										}

										function placeQueryContent(tranInfos) {
											let queryContent = document.querySelector("#queryContent");
											queryContent.innerHTML = '';
											tranInfos.forEach(function (tranInfo) {
												let infoRow = document.createElement("tr");
												let tranNo = document.createElement("td");
												tranNo.innerHTML = tranInfo.tranNo;
												let dep_time = document.createElement("td");
												dep_time.innerHTML = tranInfo.departuretime;
												let arr_time = document.createElement("td");
												arr_time.innerHTML = tranInfo.arrivaltime;
												let diff_time = document.createElement("td");
												let arrTime = arr_time.innerHTML.split(":");
												arrTime = parseInt(arrTime[0]) * 60 + parseInt(arrTime[1]);
												console.log(arrTime);
												let depTime = dep_time.innerHTML.split(":");
												depTime = parseInt(depTime[0]) * 60 + parseInt(depTime[1]);
												let timeDiff = Math.abs(depTime - arrTime);
												diff_time.innerHTML=parseInt(timeDiff/60) + ":" + timeDiff%60;
												//diff_time.innerHTML = arr_time.value - dep_time.value
												//diff_time.innerHTML = Math.abs(arrTime.toDateString() - depTime.toDateString());

												let booking = document.createElement("td");
												let booking_btn = document.createElement("button");
												booking_btn.innerHTML = "訂票";
												
												
												booking.appendChild(booking_btn);
												booking_btn.classList.add("btn");
												booking_btn.classList.add("btn-secondary");
												booking_btn.onclick = function () { };
												
												infoRow.appendChild(tranNo);
												infoRow.appendChild(dep_time);
												infoRow.appendChild(arr_time);
												infoRow.appendChild(diff_time);
												infoRow.appendChild(booking);

												queryContent.appendChild(infoRow);
											})
										}
										
										
										  var profileLi = document.getElementById("profile");
										  var editLi = profileLi.nextElementSibling;
										  var logoutLi = editLi.nextElementSibling;
										  editLi.style.display = "none";
										  logoutLi.style.display = "none";
										  profileLi.addEventListener("click", function() {
										    if (editLi.style.display === "none") {
										      editLi.style.display = "list-item";
										      logoutLi.style.display = "list-item";
										    } else {
										      editLi.style.display = "none";
										      logoutLi.style.display = "none";
										    }
										  });
										  
										  $(document).ready(function() {
											  $('#logOut').click(function() {
											    var logoutServletUrl = "/ProjectTripMaster/LogOutServlet";	    
											    window.location.href = logoutServletUrl;
											  });
											  
											  $('#goToBackstage').click(function() {
												    var goToBackstageURL = "/ProjectTripMaster/GoBackstageServlet";
												    window.location.href = goToBackstageURL;
												  });
											});
										  

											// GO TO TOP
											<!--   右下角點下後網頁到最上層的button -->
											/* <button type="button"
												class="btn btn-secondary btn-floating btn-lg"
												id="btn-back-to-top">
												<i class="fas fa-arrow-up"></i>
											</button> 
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
											}  */

									</script>
</body>

</html>