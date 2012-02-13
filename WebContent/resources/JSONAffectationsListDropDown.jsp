<%@page import="org.garde.model.Personnel"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
System.out.println("plop");
%>
[
["",""]
<s:iterator value="listAffectations" status="status">
,["<s:property value="nom"/>","<s:property value="personnelId"/>"]
<%
System.out.println((ActionContext.getContext().getValueStack().peek()).getClass().getName());
%>
</s:iterator>
]
<%
System.out.println("plip");
%>