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

<s:form>
	<s:hidden name="activite.activiteId" />
	<s:text name="label.name" />:<s:textfield name="activite.nom" size="18" />
	
	<div>			
				<s:submit action="saveActivite"
					key="label.save" cssClass="butStnd" />
				<s:submit action="mainView" key="label.cancel"
					cssClass="butStnd" />
	</div>
</s:form>

</body>
</html>