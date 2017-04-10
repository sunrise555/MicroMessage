<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<style>
		.addItem{
			width:700px;
		    height:500px;
		    background-color:#d4dcd4;    
		    margin: auto;
		    position: absolute;
		    z-index:3;
		    top: 0;
		    bottom: 0;
		    left: 0;
		    right: 0;
		    display:none;
		}
		.showMask{
			width:100%;
		    height:100%;
		    background-color:#000;
		    position:absolute;
		    top:0;
		    left:0;
		    z-index:2;
		    opacity:0.3;
		    /*兼容IE8及以下版本浏览器*/
		    filter: alpha(opacity=30);
    		display:none;
		}
		
		</style>
		<link href="<%= basePath%>resources/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js" language="javascript" type="text/javascript"></script> 
		<script type="text/javascript" src="<%= basePath%>resources/js/back/list.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="<%= basePath%>List.action" id="mainForm" method="post">
		<input type="hidden" name="currentPageIndex" id="currentPageIndex" value="${page.currentPageIndex}"/>
			<div class="right">
				<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
				<div class="addItem" id="addItem">
				<p class="g_title fix">&nbsp;&nbsp;新增列表</p>
					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">指令名称：</td>
								<td>
									<input name="newCommand" type="text" class="allInput" value="${newCommand}"/>
								</td>
								<td width="90" align="right">描述：</td>
								<td>
									<input name="newDescription" type="text" class="allInput" value="${newDescription}"/>
								</td>
	                            <td width="85" align="right">
	                            	<input type='hidden' id ="flag"/>
	                            	<input type="button" class="tabSub" value="确定" onclick="confirmAdd_modify('<%=basePath%>',$('#flag').val())"/>
	                            </td>
	                            <td width="85" align="right"><input type="button" class="tabSub" value="取消" onclick="cancel('<%=basePath%>')"/></td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%" id="add_table">
							<tbody>
								<tr>
								    <th>序号</th>
								    <th>内容</th>							
								    <th>操作</th>
								</tr>
								<tr>
									<td>1</td>
									<td><textarea name="newContents" wrap="virtual" rows="" cols="20" ></textarea></td>
									<td><a href="javascript:void" onclick="deleteRow(this)">-</a></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td><a href="javascript:addRow('add_table')">+</a></td>
								</tr>
							</tbody>
						</table>
				</div>
				</div>
				<div class="showMask" id="showMask"></div>
				<div class="rightCont">
					<p class="g_title fix">内容列表 <a class="btn03" href="javascript:addItem()">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:deleteBatch('<%=basePath%>')">删 除</a></p>
					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">指令名称：</td>
								<td>
									<input name="command" type="text" class="allInput" value="${command}"/>
								</td>
								<td width="90" align="right">描述：</td>
								<td>
									<input name="description" type="text" class="allInput" value="${description}"/>
								</td>
	                            <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th><input type="checkbox" id="all" onclick="#"/></th>
								    <th>序号</th>
								    <th>指令名称</th>
								    <th>描述</th>
								    <th>操作</th>
								</tr>
								<c:forEach items="${messageList}" var="message" varStatus="status">
									<tr <c:if test="${status.index % 2 !=0}">style='background-color:#ECF6EE;'</c:if>>
										<td><input type="checkbox"  name='operateID' value="${message.id}"/></td>
										<td>${status.index + 1}</td>
										<td>${message.command}</td>
										<td>${message.description}</td>
										<td>
											<a href="javascript:modifyItem(${message.id})">修改</a>&nbsp;&nbsp;&nbsp;
<!-- 											<a href="#" onClick="javascript:confirm('确定要删除吗？'):location.href='/DeleteOne.action?id=${message.id}':false;">删除</a> -->
											<input  type="hidden" name='deleteOneId' id='deleteOneId' value="${message.id}"/>
<!-- 											<a href="${basePath}DeleteOne.action?id=${message.id}">删除</a> -->
										   <a href="javascript:judgeDelete(${message.id})">删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class='page fix'>
							共 <b>${page.totalItemNum}</b> 条
							<c:if test="${page.currentPageIndex != 1}">
								<a href="javascript:changeCurrentPageIndex('1')" class='first'>首页</a>
								<a href="javascript:changeCurrentPageIndex('${page.currentPageIndex-1}')" class='pre'>上一页</a>
							</c:if>
							当前第<span>${page.currentPageIndex}/${page.totalPageNum}</span>页
							<c:if test="${page.currentPageIndex != page.totalPageNum}">
								<a href="javascript:changeCurrentPageIndex('${page.currentPageIndex+1}')"  class='next'>下一页</a>
								<a href="javascript:changeCurrentPageIndex('${page.totalPageNum}')"  class='last'>末页</a>
							</c:if>
							跳至&nbsp;<input id='currentPageText' type='text' value='${page.currentPageIndex}' class='allInput w28' />&nbsp;页&nbsp;
							<a href="javascript:changeCurrentPageIndex($('#currentPageText').val())" class='go'>GO</a>
						</div>
					</div>
				</div>
			</div>
	    </form>
	</body>
</html>