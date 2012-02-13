<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>

<head>
<link href="<s:url value="/resources/main.css"/>" rel="stylesheet"
	type="text/css" />
<title><s:text name="application.title" /></title>
<sx:head />
</head>
<body>
<div class="titleDiv"><s:text name="application.title" /></div>


<jsp:include page="navBar.jsp" flush="false"></jsp:include>

<s:url id="addPersonnel" action="addPersonnel" />
<s:a href="%{addPersonnel}">
	<s:text name="label.addPersonnel" />
</s:a>

<s:url id="refreshAction" action="JSONPersonnelList" />

<s:form id="filtreForm">
<div>Filtre:<s:textfield name="filtrePersonnel" onchange="dojo.event.topic.publish('/refresh');" /></div>

</s:form>

<sx:div href="%{#refreshAction}" showLoadingText="true" listenTopics="/refresh" formId="filtreForm">
<s:text name="label.loading" /> ...
</sx:div>
</body>
</html>