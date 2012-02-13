<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>

<head>
<link href="<s:url value="/resources/main.css"/>" rel="stylesheet"
	type="text/css" />
<title><s:text name="label.listeDesdispositifs" /></title>
<sx:head />
</head>
<body>
<div class="titleDiv"><s:text name="application.title" /></div>
<h1></h1>


<jsp:include page="navBar.jsp" flush="false"></jsp:include>

<s:url id="ajouterActivite" action="ajouterActivite" />
<s:a href="%{ajouterActivite}">
	<s:text name="label.ajouterActivite" />
</s:a>

<s:url id="refreshAction" action="JSONActiviteList" />

<s:form id="filtreForm">
<div>Filtre:<s:textfield name="filtreActivite" onchange="dojo.event.topic.publish('/refresh');" /> - Cette liste a été généré le <s:property value="%{now}"/> </div>
<div>Attention ! Vous devez appeler le numéro Fixe avant le numéro de portable à partir 20h.</div>
</s:form>

<sx:div href="%{#refreshAction}" showLoadingText="true" listenTopics="/refresh" formId="filtreForm">
Chargement ...
</sx:div>



</body>
</html>