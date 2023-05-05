<%@page import="model.dto.TicketDTO" %>
	<%@page import="java.util.ArrayList" %>
		<%@page import="java.util.Map.*" %>
			<%@page import="java.util.*" %>
			<%@page import="model.Utils" %>
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
					<% String basePath=request.getScheme() + "://" + request.getServerName() + ":" +
						request.getServerPort() + request.getContextPath() + "/" ; 
						%>

						<body>


							<header class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
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
															onclick="createRecord()">新增單筆資料</button>
													</div>
												</div>
											</div>
										</div>
										<div>
											<hr>
											<form method="post" id="myForm" action="">
												<table id="queryResult"
													class="table table-bordered table-striped table-sm"
													style="display: none">
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
													<tbody id="queryContent">
													</tbody>
												</table>
											</form>
										</div>
									</div>
								</section>
							</article>


							<script src="https://kit.fontawesome.com/bda18b13c5.js" crossorigin="anonymous"></script>
							<script type="text/javascript">
								function show() {
									let queryResult = document.querySelector("#queryResult");

									const xhttp = new XMLHttpRequest();
									xhttp.onload = function () {
										if (this.status === 200) {
											ticketInfos = JSON.parse(this.response);
											placeQueryContent(ticketInfos);
										}
									}
									xhttp.open("POST", "<%=basePath%>WebServices");
									xhttp.setRequestHeader("Content-type",
										"application/x-www-form-urlencoded");
									xhttp.send("service=GetAllTicketInfo");

									queryResult.style.display = "";
								}
								
								function createRecord() {
									location.href = "<%=basePath%>Bookingadmin?action=<%=Utils.ACTION_CREATE%>";
								}

								function placeQueryContent(ticketInfos) {
									console.log(ticketInfos);
									let queryContent = document.querySelector("#queryContent");
									queryContent.innerHTML = '';
									ticketInfos.forEach(function (ticketInfo) {
										let infoRow = document.createElement("tr");
										let id = document.createElement("td");
										id.innerHTML = ticketInfo.TicketID;
										let tranNo = document.createElement("td");
										tranNo.innerHTML = ticketInfo.TranNo;
										let seat = document.createElement("td");
										seat.innerHTML = ticketInfo.Seat;
										let dep_st = document.createElement("td");
										dep_st.innerHTML = ticketInfo.Departure_ST;
										let des_st = document.createElement("td");
										des_st.innerHTML = ticketInfo.Destination_ST;
										let dep_time = document.createElement("td");
										dep_time.innerHTML = ticketInfo.Departure_time;
										let arr_time = document.createElement("td");
										arr_time.innerHTML = ticketInfo.Arrival_time;
										let price = document.createElement("td");
										price.innerHTML = ticketInfo.price;
										let date = document.createElement("td");
										date.innerHTML = ticketInfo.Date;

										let updt = document.createElement("td");
										let updt_btn = document.createElement("button");
										let updt_icon = document.createElement("i");

										updt_icon.className = "fa-solid fa-pen-to-square";
										updt_btn.appendChild(updt_icon);
										updt.appendChild(updt_btn);
										updt_btn.classList.add("btn");
										updt_btn.classList.add("btn-light");
										updt_btn.setAttribute("onclick", "formSubmit(ticketInfo.TicketID, 'update')");


										let del = document.createElement("td");
										let del_btn = document.createElement("button");
										let del_icon = document.createElement("i");
										del_icon.className = "fa-solid fa-trash-can";
										del_btn.appendChild(del_icon);
										del.appendChild(del_btn);
										del_btn.classList.add("btn");
										del_btn.classList.add("btn-light");
										del_btn.onclick = function () { deleteTarget(ticketInfo.TicketID) };



										infoRow.appendChild(id);
										infoRow.appendChild(tranNo);
										infoRow.appendChild(seat);
										infoRow.appendChild(dep_st);
										infoRow.appendChild(des_st);
										infoRow.appendChild(dep_time);
										infoRow.appendChild(arr_time);
										infoRow.appendChild(price);
										infoRow.appendChild(date);
										infoRow.appendChild(updt);
										infoRow.appendChild(del);

										queryContent.appendChild(infoRow);
									});
								}
								function deleteTarget(id) {
									const xhttp = new XMLHttpRequest();
									xhttp.onload = function () {
										if (this.status === 200) {
											deleteResult = JSON.parse(this.response);
											alert(deleteResult.msg);
											document.querySelector("#search").click();
										}
									}
									xhttp.open("POST", "<%=basePath%>WebServices");
									xhttp.setRequestHeader("Content-type",
										"application/x-www-form-urlencoded");
									xhttp.send("service=DeleteTicketInfo&id=" + id);
								}


								// function formSubmit(id, action) {
								// 	let form = document.getElementById("myForm");
								// 	document.getElementById("updateTarget").value = id;
								// 	document.getElementById("action").value = action;
								// 	console.log("123");
								// 	form.submit();
								// } 
							</script>
						</body>

					</html>