<%@page import="org.garde.actions.ShowPlanningAstreintesAction.DayAstreinte"%>
<%@page import="org.garde.model.Activite"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
int unique = 0;
%>
<html>

<head>
<link href="<s:url value="/resources/main.css"/>" rel="stylesheet"
	type="text/css" />
<title><s:text name="application.title" />
</title>
<sx:head />

</head>
<body>







	<div class="titleDiv">
		<s:text name="application.title" />
	</div>


	<jsp:include page="navBar.jsp" flush="false"></jsp:include>

	
		<script type="text/javascript">
			dojo.event.topic.subscribe("/value", function(text, date, widget) {
				document.forms['filtreForm'].submit();

			});
		</script>
		<s:form id="filtreForm">
			<div>
				Mois visualisés: <sx:datetimepicker name="monthDate" valueNotifyTopics="/value"></sx:datetimepicker>
				- Voir uniquement les activités gérés: <s:checkbox name="activiteGere" onchange="dojo.event.topic.publish('/value');"></s:checkbox>				
				 - Ce tableau a été généré le <s:property value="%{now}"/>
		 	</div>
		</s:form>

		<table class='astreinte'>
			<tr>
				<th class='coldate'></th>
				<s:iterator value="listActivites" status="status">
					 <th class='colactivite'>
					 <s:url id="editPlanning" action="editPlanningAstreinte">																									
						<s:param name="cellActiviteId" value="activiteId" />
						<s:param name="monthDateAsString" value="monthDateAsString" />
					 </s:url>
					  
					  
					 <s:a href="%{editPlanning}" ><s:property value="nom" /></s:a>
					 </th>
				</s:iterator>
			</tr>
			<s:iterator value="listDayAstreintes" status="status"
				var="dayAstreinte"  status="rowstatus"> 
				<s:if test="#rowstatus.odd == true">
					<tr class='odd'>
				</s:if>
				<s:else>
					<tr class='even'>
				</s:else>
				
					<td><s:property value="longDate" />
					</td>
					<s:iterator value="listActivites" status="status" var='activite'>
						<td>
								
								<ul>
								<s:iterator value="contextAstreinteList" var='astreinte'>
									<li><s:property value="#astreinte.personnel.nom" /> de <s:property value="%{getInHour(debut)}" /> à <s:property value="%{getInHour(fin)}" />					
								</s:iterator>
								</ul>
						</td>
					</s:iterator>					
					</tr>
			</s:iterator>
		</table>


</body>
</html>