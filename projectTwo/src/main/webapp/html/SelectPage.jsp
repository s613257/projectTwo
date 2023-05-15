<%@page import="model.dto.PriceInfoDTO" %>
	<%@page import="java.util.ArrayList" %>
		<%@page import="java.util.Map.*" %>
			<%@page import="java.util.*" %>
				<%@page import="model.dto.StationInfoDTO" %>
					<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
						<!DOCTYPE html>
						<html>

						<head>
							<meta charset="UTF-8">
							<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
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

						<% Map<Set<String>, Integer> priceInfos = (Map<Set<String>, Integer>)
								request.getAttribute("priceInfos");
								String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
								request.getServerPort()
								+ request.getContextPath() + "/";
								%>


								<body onload="showPrice()">


									<form method="POST" action="">
										<header
											class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
											<div class="container-fluid d-flex align-items-center">
												<h1 class="d-flex align-items-center fs-4 text-white mb-0">
													Teavel Master</h1>
												<a href="/docs/5.0/examples/cheatsheet-rtl/" class="ms-auto link-light"
													hreflang="ar">RTL cheatsheet</a>
											</div>
										</header>
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
																<label class="input-smalllabel"
																	for="select_location01">起程站</label>
																<select id="Departure_ST" name="Departure_ST"
																	class="form-control mt-0 select-type01" title="出發站"
																	onchange="showPrice()">
																	<% List<StationInfoDTO> stationList = (ArrayList
																		<StationInfoDTO>)
																			request.getAttribute("stationList");
																			for (StationInfoDTO st : stationList) {
																			%>
																			<option value="<%=st.getStationID()%>">
																				<%=st.getStationName()%>
																			</option>
																			<% } %>
																</select>
															</div>
															<div class="col-input col">
																<label class="input-smalllabel"
																	for="select_location02">到達站</label>
																<select id="Destination_ST" name="Destination_ST"
																	class="form-control mt-0 select-type01" title="到達站"
																	onchange="showPrice()">
																	<% for (StationInfoDTO st : stationList) { %>
																		<option value="<%=st.getStationID()%>">
																			<%=st.getStationName()%>
																		</option>
																		<% } %>
																</select>
															</div>

															<div class="col-input col">
																<label class="input-smalllabel" for="typeOfTicket"> 票種
																</label>
																<select class="form-control mt-0 select-type01"
																	title="票種" id="typeOfTicket">
																	<option value="tot-1" selected>單趟</option>
																	<!-- <option value="tot-2" selected>來回</option> -->
																</select>
															</div>
															<div class="col-input col-2">
																<label class="input-smalllabel"
																	for="departure_date">出發日期</label>
																<input id="departure_date" type="Date"
																	class="form_date form-control">
															</div>
															<div class="col-input col-2">
																<label class="input-smalllabel"
																	for="Departure_time">出發時刻</label>
																<input id="Departure_time" type="Time"
																	class="form_date form-control">
															</div>
															<div class="col-input col">
																<label class="input-smalllabel" for="typesofticket">
																	參考票價 </label> <input type="text" id="price"
																	name="price" value=""
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

<script src="https://kit.fontawesome.com/bda18b13c5.js" crossorigin="anonymous"></script>
									<script type="text/javascript">
										var priceInfos = new Map();
										let tmpS;
										<%for (Entry < Set < String >, Integer > pk : priceInfos.entrySet()) {%>
											tmpS = new Set();
											tmpS.add(<%=pk.getKey().toArray()[0] %>);
											tmpS.add(<%=pk.getKey().toArray()[1] %>);
											priceInfos.set(tmpS, <%=pk.getValue() %>)
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


										function search() {
											let departure_ST = document.querySelector("#Departure_ST");
											let destination_ST = document.querySelector("#Destination_ST");
											let typeOfTicket = document.querySelector("#typeOfTicket");
											let departure_date = document.querySelector("#departure_date");
											let departure_time = document.querySelector("#Departure_time");
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
												dep_time.innerHTML = tranInfo.departure_time;
												let arr_time = document.createElement("td");
												arr_time.innerHTML = tranInfo.arrival_time;
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

									</script>
								</body>

						</html>