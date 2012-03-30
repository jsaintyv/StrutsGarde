<%@page import="org.garde.model.Astreinte"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>

<head>
<link href="<s:url value="/resources/main.css"/>" rel="stylesheet"
	type="text/css" />
<title><s:text name="label.listeDesdispositifs" />
</title>
<sx:head />
</head>
<body>
<script type="text/javascript">
		$.ajaxSetup({ 
	        scriptCharset: "utf-8" , 
	        contentType: "application/json; charset=utf-8"});		
		</script>
	<div class="titleDiv">
		<s:text name="application.title" />
	</div>

	<s:form id='main'>
		<s:hidden name="activite.activiteId" />
		<s:hidden name="astreinteId" />
		<div>
			<s:text name="label.name" />
			:
			<s:textfield name="activite.nom" size="18" />
		</div>
		<div>
			<s:text name="label.comment" />
			:
		</div>
		<p>
			<s:textarea name="activite.comment" rows="3" cols="30" />
		</p>

		<div>
			<s:submit action="saveActivite" key="label.save" cssClass="butStnd" />
			<s:submit action="mainView" key="label.cancel" cssClass="butStnd" />
		</div>

		<div>Astreinte:</div>
		<table>
			<s:iterator value="%{getAstreintes()}" var="astreinte"
				status="status2">
				<tr
					class="<s:if test="#status2.even">astreinteeven</s:if><s:else>astreinteodd</s:else>">
					<td class='perNom'><s:property
							value="#astreinte.personnel.nom" /> <s:property
							value="#astreinte.personnel.prenom" />&nbsp;</td>

					<td class='perTelMobile'><s:property
							value="#astreinte.personnel.telMobile" />&nbsp;</td>
					<td class='perTelPro'><s:property
							value="#astreinte.personnel.telPro" />&nbsp;</td>
					<td class='perTelPerso'><s:property
							value="#astreinte.personnel.telPerso" />&nbsp;</td>
					<td class='astreinteDe'>De <s:property
							value="%{getInHour(debut)}" />&nbsp;</td>
					<td class='astreinteA'>A <s:property value="%{getInHour(fin)}" />&nbsp;</td>
					
					<td class='astreinteA'>
					<s:url id="deleteAction" action="supprimerAstreinteFromActivite" includeParams="none">																											
					</s:url>	
						
					<%
					String script = "javascript:document.forms['0'].action='" + ActionContext.getContext().getValueStack().findValue("deleteAction") + "';document.forms['0'].astreinteId.value='" + ((org.garde.model.Astreinte)ActionContext.getContext().getValueStack().findValue("astreinte")).getAstreinteId() + "';document.forms['0'].submit();"; 
					%>
					<a onclick="<%=script%>">Supprimer</a>
					</td>
				</tr>
			</s:iterator>
		</table>

		<p>
		
		<s:url id="jsonAffectations" value="JSONAffectationsListDropDown.action">
				<s:param name="cellActiviteId" value="activite.activiteId"></s:param>
		</s:url>
			<%
			String cboId= "200";
			String dataUrl ="" + ActionContext.getContext().getValueStack().findValue("jsonAffectations");
			 
			 %>
			<table>
				<tr>
				<td>
			Ajouter : <input dojoType="struts:ComboBox" dataUrl="<%=dataUrl%>"
				id="newPersonnel" autoComplete="true" keyName="personnelId"
				name="personnelNom" loadOnType="true" loadMinimum="0"
				visibleDownArrow="true" />
			<script language="JavaScript" type="text/javascript">djConfig.searchIds.push("newPersonnel");</script>
			</td>
			<td>
			<p>
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.8a8" cssClass="butStnd" />
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.8a14"
				cssClass="butStnd" />
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.14a20"
				cssClass="butStnd" />
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.8a13"
				cssClass="butStnd" />
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.13a20"
				cssClass="butStnd" />
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.20a8"
				cssClass="butStnd" />
			</p>
			<p>
			<s:textfield name="heureDebut"  size="4"/>
			h <s:textfield name="minuteDebut"  size="4"/> min
			
			<s:textfield name="heureFin"  size="4"/>
			h <s:textfield name="minuteFin" size="4" />
			
			<s:submit action='ajouterAstreinteToActivite' name="horaires" key="label.specifique"
				cssClass="butStnd" />
			</p>
			</td></tr></table>
		</p>
	</s:form>

</body>
</html>