<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">

		<li class="nav-item"><a class="nav-link collapsed"
			href="${pageContext.request.contextPath}/dashboard"> <i class="bi bi-grid"></i> <span>Trang chủ</span>
		</a></li>

		<li class="nav-item"><a class="nav-link collapsed"
			href="${pageContext.request.contextPath}/room"> <i class="bi bi-question-circle"></i> <span>Quản lý phòng</span>
		</a></li>
		
		<li class="nav-item"><a class="nav-link collapsed"
			href="${pageContext.request.contextPath}/employee"> <i class="bi bi-person"></i> <span>Quản lý nhân viên</span>
		</a></li>

		<li class="logout.jsp"><a class="nav-link collapsed"
			href="${pageContext.request.contextPath}/logout"> <i class="bi bi-box-arrow-in-right"></i> <span>Đăng xuất</span>
		</a></li>

	</ul>

</aside>