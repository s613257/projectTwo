<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="index.jsp">Travel Master</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="#!">Settings</a></li>
					<li><a class="dropdown-item" href="#!">Activity Log</a></li>
					<li>
						<hr class="dropdown-divider" />
					</li>
					<li><a class="dropdown-item" href="#!">Logout</a></li>
				</ul></li>
		</ul>
	</nav>

	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">後台管理者</div>
						<div class="sb-nav-link-icon">
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseLayouts" aria-expanded="false"
								aria-controls="collapseLayouts"> <i
								class="fa-regular fa-user p-2"></i> 使用者 <span
								class="sb-sidenav-collapse-arrow"> <i
									class="fas fa-angle-down"></i>
							</span>
							</a>
						</div>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="layout-static.jsp">Static
									Navigation</a> <a class="nav-link" href="layout-sidenav-light.jsp">Light
									Sidenav</a> <a class="nav-link" href="login.jsp">Login</a> <a
									class="nav-link" href="register.jsp">Register</a> <a
									class="nav-link" href="password.jsp">Forgot Password</a>
							</nav>
						</div>
						<div class="sb-nav-link-icon">
							<a class="nav-link" href="index.jsp"> <i
								class="fa-solid fa-location-dot p-2 "> </i> 行程
							</a>
						</div>
						<div class="sb-nav-link-icon">
							<a class="nav-link" href="index.jsp"> <i
								class="fa-solid fa-ticket p-2"></i> 訂票
							</a>
						</div>
						<div class="sb-nav-link-icon">
							<a class="nav-link" href="index.jsp"> <i
								class="fa-regular fa-comment-dots p-2"></i> 論壇
							</a>
						</div>
						<div class="sb-nav-link-icon">
							<a class="nav-link" href="index.jsp"> <i
								class="fa-solid fa-users p-2"></i> 陪玩
							</a>
						</div>
						<div class="sb-nav-link-icon">
							<a class="nav-link" href="index.jsp"> <i
								class="fa-solid fa-cart-shopping p-2"></i> 訂單管理
							</a>
						</div>
					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Version : 1.0.0</div>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">