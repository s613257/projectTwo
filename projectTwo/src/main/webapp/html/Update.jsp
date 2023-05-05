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

<body>
	<%
	TicketDTO tk = (TicketDTO) request.getAttribute("tk");
	%>
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
		<article class="container">
			<div class="aside">
				<div class="section">
					<h2 style="text-align: center;">修改資料</h2>
					<form>
						<div style="margin: 5px;">
							<label for="" class="t1">訂票編號：</label> <input type="text" id=""
								name="" value="<%=tk.getTicketID()%>" disabled
								class="form-control" autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">班次：</label> <input type="text" id=""
								name="" value="<%=tk.getTranNo()%>" class="form-control"
								autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">座位：</label> <input type="text" id=""
								name="" value="<%=tk.getSeat()%>" class="form-control" autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">出發站：</label> <input type="text" id=""
								name="" value="<%=tk.getDeparture_ST()%>" class="form-control"
								autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">抵達站：</label> <input type="text" id=""
								name="" value="<%=tk.getDestination_ST()%>" class="form-control"
								autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">出發時間：</label> <input type="text" id=""
								name="" value="<%=tk.getDeparture_time()%>" class="form-control"
								autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">抵達時間：</label> <input type="text" id=""
								name="" value="<%=tk.getArrival_time()%>" class="form-control"
								autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">票價：</label> <input type="text" id=""
								name="" value="<%=tk.getPrice()%>" class="form-control"
								autofocus>
						</div>
						<div style="margin: 5px;">
							<label for="" class="t1">訂票時間：</label> <input type="date" id=""
								name="" value="<%=tk.getDate()%>" class="form-control" autofocus>
						</div>
					</form>
					<div style="text-align: center; padding: 30px;">
						<button type="submit" class="btn btn-dark" style="margin: 10px;">確定訂票</button>
						<button type="reset" class="btn btn-dark" style="margin: 10px;">取消訂票</button>
					</div>
				</div>
			</div>
		</article>
	</form>
</body>

</html>