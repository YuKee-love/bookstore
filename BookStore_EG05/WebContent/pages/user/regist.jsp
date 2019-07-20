<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		var i = 1;
		//给验证码图片绑定单击事件  点击刷新验证码
		//每次请求code.jpg  servlet都会返回一张新的验证码图片
		$("#codeImg").click(function(){
			/*
				谷歌浏览器没有问题
				火狐和IE都有问题：点击不会发起新的请求得到图片
					原因：浏览器缓存
						当火狐和IE浏览器解析同一个url地址时，如果url地址不变，浏览器认为该地址是重复请求，直接从缓存中取出数据使用，不会发起新的请求
						get请求的：url+参数列表，post请求没有缓存问题
					解决： 如果每次请求的地址不一样，浏览器都会向服务器发起新的请求
						在url地址后拼接一个变化的参数列表，欺骗浏览器
			*/
			this.src = "code.jpg?t="+(i++);
		});
		
		
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<%-- <%
									String errorMsg = (String)request.getAttribute("errorMsg") ;
									if(errorMsg == null){
										errorMsg = "";
									}
								%>
								
								<span class="errorMsg"><%=errorMsg%></span> --%>
								<%-- <span class="errorMsg"><%=request.getAttribute("errorMsg")==null?"":request.getAttribute("errorMsg") %></span> --%>
								<span class="errorMsg">${errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="POST">
									<label>用户名称：</label>
									<!-- 如果注册失败转发回来，request对象中就包含注册失败的参数
									
											获取用户提交的表单参数：String request.getParameter(name)
											获取的是我们自己在java代码中设置的属性值：Object request.getAttribute("errorMsg")
									 -->
									
									<input type="hidden" name="type" value="regist"/>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img alt="" id="codeImg" src="code.jpg" style="float: right; margin-right: 40px;width:90px;height:40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>