<%@page import="model.dto.TicketDTO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Form</title>
<style>
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
	<%
		TicketDTO tk = (TicketDTO)request.getAttribute("tk");
	%>
	<form method="post" action="UpdateBooking">
		<fieldset>
			<legend>
				<strong>(必填)</strong>
			</legend>
			<div class="st1">
				<label for="" class="t1">訂票編號：</label> <%=tk.getTicketID() %>
				<input type="hidden" name = "empno" value="<%=tk.getTicketID() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">班次：</label><input type="text" id=""
					name="ename" size="10" value="<%=tk.getTranNo() %>" autofocus>
			</div>
			<div class="st1">
				<label for="" class="t1">座位：</label> <input type="date"
					name="hiredate" id="" value="<%=tk.getSeat() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">出發站：</label> <input type="text"
					name="salary" id="" size="30" value="<%=tk.getDeparture_ST() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">抵達站：</label> <input type="text"
					name="deptno" id="" size="30" value="<%=tk.getDestination_ST() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">出發時間：</label> <input type="text" name="title"
					id="" size="30" value="<%=tk.getDeparture_time() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">抵達時間：</label> <input type="text" name="title"
					id="" size="30" value="<%=tk.getArrival_time() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">票價：</label> <input type="text" name="title"
					id="" size="30" value="<%=tk.getPrice() %>">
			</div>
			<div class="st1">
				<label for="" class="t1">訂票日期：</label> <input type="text" name="title"
					id="" size="30" value="<%=tk.getDate() %>">
			</div>
		</fieldset>
		<div class="sub">
			<input type="submit" value="修改"> <input type="reset"
				value="取消">
		</div>
	</form>
</body>

</html>