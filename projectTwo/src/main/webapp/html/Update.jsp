<%@page import="model.dto.TicketDTO"%>
<%@page import="java.util.*"%>
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
<title>SelectPage</title>
<style>
.Traveltime {
	font-size: small;
}

td {
	font-size: large;
}
fieldset {
	width: 500px;
	border: 1px solid cornflowerblue;
	border-radius: 15px;
	margin: 10px;
}
legend {
	color: rgb(11, 53, 240);
	/* text-align: center; */
	margin-left: 65px;
}
strong {
	color: red;
}
.st1 {
	width: 450px;
	/* border-bottom: 3px dashed rgb(77, 77, 72); */
	margin: 10px;
	padding: 5px;
}
.sub {
	width: 450px;
	margin: 20px;
	text-align: center;
}
.t1 {
	width: 100px;
	float: left;
	text-align: right;
}
textarea {
	resize: none;
}
</style>
</head>

<body>
	<form>
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
			<%
			TicketDTO tk = (TicketDTO) request.getAttribute("tk");
			%>
			<form method="post" action="UpdateBooking">
				<fieldset>
					<legend>
						<strong>(必填)</strong>
					</legend>
					<div class="st1">
						<label for="" class="t1">訂票編號：</label>
						<%=tk.getTicketID()%>
						<input type="hidden" name="empno" value="<%=tk.getTicketID()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">班次：</label><input type="text" id=""
							name="ename" size="10" value="<%=tk.getTranNo()%>" autofocus>
					</div>
					<div class="st1">
						<label for="" class="t1">座位：</label> <input type="date"
							name="hiredate" id="" value="<%=tk.getSeat()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">出發站：</label> <input type="text"
							name="salary" id="" size="30" value="<%=tk.getDeparture_ST()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">抵達站：</label> <input type="text"
							name="deptno" id="" size="30" value="<%=tk.getDestination_ST()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">出發時間：</label> <input type="text"
							name="title" id="" size="30" value="<%=tk.getDeparture_time()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">抵達時間：</label> <input type="text"
							name="title" id="" size="30" value="<%=tk.getArrival_time()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">票價：</label> <input type="text"
							name="title" id="" size="30" value="<%=tk.getPrice()%>">
					</div>
					<div class="st1">
						<label for="" class="t1">訂票日期：</label> <input type="text"
							name="title" id="" size="30" value="<%=tk.getDate()%>">
					</div>
				</fieldset>
				<div class="sub">
					<input type="submit" value="修改"> <input type="reset"
						value="取消">
				</div>
			</form>
	</form>
</body>

</html>