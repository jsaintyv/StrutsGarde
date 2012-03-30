<%@page import="org.garde.model.Personnel"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
[
["",""]
<s:iterator value="listAffectations" status="status">
,["<s:property value="nom" escapeJavaScript="true" escapeHtml="false"  />","<s:property  value="personnelId" escapeJavaScript="true" escapeHtml="false"/>"]
<%
System.out.println((ActionContext.getContext().getValueStack().peek()).getClass().getName());
%>
</s:iterator>
]
