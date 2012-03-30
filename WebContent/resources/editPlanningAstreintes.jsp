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
	<div class="titleDiv">Ajouter : h à h 
		<s:text name="application.title" />
	</div>


	<jsp:include page="navBar.jsp" flush="false"></jsp:include>

	
		<script type="text/javascript">
			dojo.event.topic.subscribe("/value", function(text, date, widget) {
				document.forms['filtreForm'].submit();
				filtreForm
			});

			$.ajaxSetup({ 
		        scriptCharset: "utf-8" , 
		        contentType: "application/json; charset=utf-8"
		});

					
		</script>
		<s:form id="filtreForm">
			<sx:datetimepicker name="monthDate" valueNotifyTopics="/value"></sx:datetimepicker>
			
			<s:hidden name="cellActiviteId" value="%{cellActiviteId}" />
		</s:form>

		<table class='astreinte'>
			<tr>
				<th class='coldate'></th>
				<th class='colEditActivite'>
					<s:property value="cellActivite.nom" /> 
				</th>				
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
					
						<td>
								<s:url id="refreshAction" action="JSONPlanningAstreinteCellule" includeParams="none">																
									<s:param name="cellDateAsString"><s:property value="#dayAstreinte.time"/> </s:param>
									<s:param name="cellActiviteId" value="#request.cellActiviteId" />
								</s:url>
								<s:set name="divId">cellT<s:property value="#dayAstreinte.time" />ID<s:property value="#request.cellActiviteId" /></s:set>
								<s:url id="jsonAffectations" value="JSONAffectationsListDropDown.action">
									<s:param name="cellActiviteId" value="#request.cellActiviteId"></s:param>
								</s:url>
								<%
								Integer activiteId =((Activite)ActionContext.getContext().getValueStack().findValue("cellActivite")).getActiviteId();
								String cboId = "cboT" + ((DayAstreinte) ActionContext.getContext().getValueStack().findValue("dayAstreinte")).getLiteralDate() + "ID" + activiteId;
								String dataUrl ="" + ActionContext.getContext().getValueStack().findValue("jsonAffectations");
								String keyName ="personnelIdT" + ((DayAstreinte) ActionContext.getContext().getValueStack().findValue("dayAstreinte")).getLiteralDate() + "ID" + activiteId;
								%>
																
																
								<s:set name="cellFormId">cellFormT<s:property value="#dayAstreinte.time" />ID<s:property value="#request.cellActiviteId" /></s:set>
																
								
																
								<s:form id="%{cellFormId}" action="JSONPlanningAstreinteCellule" method="POST">
									<sx:div id="%{divId}" href="%{#refreshAction}" showLoadingText="false" formId="%{cellFormId} " />
									<p>	
									<s:hidden name="cellDateAsString" value='%{#dayAstreinte.time}' /> 
									<s:hidden name="cellActiviteId" value="%{#request.cellActiviteId}" />
									
								<table>
								<tr>
									<td>	
									Ajouter :
									<input dojoType="struts:ComboBox"  dataUrl="<%=dataUrl%>" id="<%=cboId%>" autoComplete="true" keyName="<%=keyName%>"  name="personnelNom" loadOnType="true" loadMinimum="0" visibleDownArrow="true" />
									<script language="JavaScript" type="text/javascript">djConfig.searchIds.push("<%=cboId%>");</script>
								 	</td>
									<td>
									<p>						
								<sx:submit targets="%{divId}" name="horaires"  key="label.8a8" 
									cssClass="butStnd" />
								<sx:submit targets="%{divId}" name="horaires"  key="label.8a14"
									cssClass="butStnd"  />										
								<sx:submit targets="%{divId}" name="horaires" key="label.14a20"
									cssClass="butStnd"  />	
									<sx:submit targets="%{divId}" name="horaires"  key="label.8a13"
									cssClass="butStnd"  />										
								<sx:submit targets="%{divId}" name="horaires" key="label.13a20"
									cssClass="butStnd"  />
								<sx:submit targets="%{divId}" name="horaires" key="label.20a8"
									cssClass="butStnd"  />
									</p>
									<p>
								<s:textfield name="heureDebut" size="4" />h<s:textfield name="minuteDebut" size="4" />min
								 à <s:textfield name="heureFin" size="4" />h<s:textfield name="minuteFin" size="4" />min 	
								
								<sx:submit targets="%{divId}" name="horaires" key="label.specifique"
									cssClass="butStnd"  /></p>
									</td>
									</tr></table>	
									</p>	
									
								</s:form>	
								

						</td>
										
					</tr>
			</s:iterator>
		</table>


</body>
</html>