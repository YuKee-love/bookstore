<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<!-- 引入登录状态栏提取页面 -->
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h4>一个订单都木有，快去购买吧！！！</h4>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
					<c:forEach items="${list }" var="order">
						<tr>
							<td>${order.orderId }</td>
							<!-- 
								fmt： 了解
								value属性：要处理的日期时间
							
								type属性：
									date 只显示 日期
									time 只显示时间
									both 都显示
								dateStyle属性：
									日期风格
									full 全风格
									long 长风格
									short 短风格
								timeStyle:
									full/long/short
							 -->
							<td><fmt:formatDate type="both" dateStyle="full" value="${order.createTime }"/></td>
							<td>${order.totalAmount }</td>
							<td>
								<c:choose>
									<c:when test="${order.state==0 }">未发货</c:when>
									<c:when test="${order.state==1 }"><a href="OrderServlet?type=takeGoods&orderId=${order.orderId }">确认收货</a></c:when>
									<c:when test="${order.state==2 }">交易完成</c:when>
								</c:choose>
							</td>
							<td><a href="#">查看详情</a></td>
						</tr>
					</c:forEach>
				</table>
			
			</c:otherwise>
		</c:choose>
		


	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2018 </span>
	</div>
</body>
</html>