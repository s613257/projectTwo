<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
			crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
		<title>Insert title here</title>
		<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.min.css" rel="stylesheet">
		<style>
			.aside {
				border: 2px solid rgb(215, 211, 211);
				border-radius: 15px;
				background-color: #FFF;
				box-shadow: 3px 3px 3px gray;
				margin: 10px 0px 0px 0px;
			}

			.section {
				/* border: solid gray; */
				/* background-color: #ffffff; */
				border-radius: 5px;
				margin: 10px 0;
				padding: 40px 40px 40px 40px;
				line-height: 2em;
			}

			body {
				font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI",
					Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica,
					Arial, sans-serif;
			}
		</style>

	</head>

	<body onload="initSeats();">

		<header class="bd-header bg-dark py-3 d-flex align-items-stretch border-bottom border-dark">
			<div class="container-fluid d-flex align-items-center">
				<h1 class="d-flex align-items-center fs-4 text-white mb-0">
					Teavel Master</h1>
				<a href="/docs/5.0/examples/cheatsheet-rtl/" class="ms-auto link-light" hreflang="ar">RTL cheatsheet</a>
			</div>
		</header>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<table class="table">
					<tr class="row">
						<td class="col-7">
							<div class="article">
								<div class="section">
									<div class="row">
										<h2 class="col  text-center">台灣高鐵座位圖</h2>
									</div>
									<div class="row">
										<div class="col">
											<table class="table table-bordered border-dark table-sm">
												<tbody id="seats_container" class="text-center">
													<!-- Init by function initSeats() -->
												</tbody>
											</table>
										</div>

									</div>
								</div>
							</div>
						</td>
						<td class="col-1"></td>
						<td class="col-4">
							<div class="aside">
								<div class="row">
									<div class="section">
										<h2 style="text-align: center;">訂票紀錄</h2>
										<form>
											<div class="row">
												<label for="name">會員姓名： </label> <input type="email"
													class="form-control" id="name">
											</div>
											<div class="row">
												<label for="emailAddr">電子信箱：</label> <input type="email"
													class="form-control" id="emailAddr" aria-describedby="emailHelp"
													placeholder="Enter email">
												<small id="emailHelp"
													class="form-text text-muted">我們會將訂票紀錄寄送至您的電子信箱。</small>
											</div>
											<div class="row">
												<div class="form-floating mb-3 col-6">
													<div class="form-control" id="DepartureTime"
														style="text-align: center;">2023/05/31</div>
													<label for="DepartureTime">發車時間:</label>
												</div>
												<div class="form-floating mb-3 col-6">
													<div class="form-control" id="ArrivalTime"
														style="text-align: center;">2023/05/31</div>
													<label for="ArrivalTime">抵達時間:</label>
												</div>
											</div>
											<div class="row">
												<div class="form-floating mb-3 col-6">
													<div class="form-control" id="DepartureStation"
														style="text-align: center;">桃園</div>
													<label for="DepartureStation">出發站：</label>
												</div>
												<div class="form-floating mb-3 col-6">
													<div class="form-control" id="ArrivalStation"
														style="text-align: center;">板橋</div>
													<label for="ArrivalStation">抵達站：</label>
												</div>
											</div>
											<div class="row mb-3">
												<label for="selectedSeats">已選座位： </label>
												<div class="form-control" id="selectedSeats">&nbsp;</div>
											</div>
											<div class="row">
												<div class="form-floating mb-3 col-6">
													<div class="form-control" id="price" style="text-align: center;">xx元
													</div>
													<label for="price">每張單價：</label>
												</div>
												<div class="form-floating mb-3 col-6">
													<div class="form-control" id="priceTotal"
														style="text-align: center;">xx元</div>
													<label for="priceTotal">總共票價：</label>
												</div>
											</div>
											<div style="text-align: center;">
												<button type="submit" class="btn btn-dark" id="submit">確定訂票</button>
												<button type="reset" class="btn btn-dark">取消訂票</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-1"></div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>


		<script>
			function createBtn(content) {
				let td = document.createElement("td");
				let input_btn = document.createElement("input");
				td.appendChild(input_btn);
				input_btn.id = content;
				input_btn.name = "seat";
				input_btn.type = "checkbox";
				input_btn.classList.add("btn-check");
				input_btn.autocomplete = "off";
				input_btn.onchange = show;

				let input_btn_lbl = document.createElement("label");
				td.appendChild(input_btn_lbl);
				input_btn_lbl.classList.add("btn");
				input_btn_lbl.classList.add("btn-secondary");
				input_btn_lbl.setAttribute("for", content);
				input_btn_lbl.innerHTML = content;
				return td;
			}

			function initSeats() {
				const rowNum = 10;
				let seatChar = ['A', 'B', 'C', 'D', 'E'];
				let seats_container = document.getElementById("seats_container");
				let tr = document.createElement("tr");
				let cnt = 0;
				for (let i = 0; i < seatChar.length + 1; i++) {
					if (i == 3) {
						let sideWay = document.createElement("td");
						sideWay.innerHTML = "走道";
						sideWay.classList.add("align-middle");
						sideWay.rowSpan = rowNum;
						sideWay.style = "text-align:center; width:55px";
						tr.appendChild(sideWay);
						continue;
					}
					tr.appendChild(createBtn("01" + seatChar[cnt]));
					cnt++;
				}
				seats_container.appendChild(tr);
				for (let i = 2; i <= rowNum; i++) {
					let trTmp = document.createElement("tr");
					for (let j = 0; j < seatChar.length; j++) {
						trTmp.appendChild(createBtn(i.toString().padStart(2, '0')
							+ seatChar[j]));
					}
					seats_container.appendChild(trTmp);
				}
			}
			function show() {
				let selectedSeats = document.getElementsByName("seat");
				let p = document.getElementById("selectedSeats");
				p.innerHTML = "&nbsp;";
				let p_innerHTML = "";
				let selectedCnt = 0;
				for (let i = 0; i < selectedSeats.length; i++) {
					if (selectedSeats[i].checked == true) {
						selectedCnt++;
						p_innerHTML = p_innerHTML + " [ " + selectedSeats[i].id
							+ " ] ";
					}
				}
				if (selectedCnt > 0) {
					p.innerHTML = p_innerHTML;
				}
			}

			$(function () {
				$('#submit').on("click", function () {
					Swal.fire('您的訂票已完成!', '請至會員專區查閱電子票券','success');
				})
			})
		</script>
	</body>

	</html>