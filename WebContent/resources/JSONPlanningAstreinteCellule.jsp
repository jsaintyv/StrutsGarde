<%@page import="org.garde.model.Astreinte"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>	
<ul>
	<s:iterator value="listAstreinte" var='astreinte'>
		<li><s:property value="#astreinte.personnel.nom" /> de <s:property value="%{getInHour(debut)}" /> à <s:property value="%{getInHour(fin)}" /> 
		
		<s:url id="loadActivite"
					action="loadActivite">
					<s:param name="activite.activiteId" value="activiteId" />
				</s:url> <s:a href="%{loadActivite}">
					<s:property value="nom" />
				</s:a></td>
		<s:url id="deleteAction" action="JSONPlanningAstreinteCelluleSuppression" includeParams="none">		
			<s:param name="astreinteId"><s:property value="#astreinte.astreinteId"/> </s:param>														
			<s:param name="cellDate"><s:property value="#request.cellDate"/> </s:param>
			<s:param name="cellActiviteId"><s:property value="#request.cellActiviteId"/></s:param>
		</s:url>		
		
		<s:set name="divId">cellT<s:property value="#request.cellDateAsString" />ID<s:property value="#request.cellActiviteId" /></s:set>
		<s:set name="cellFormId">cellFormT<s:property value="#request.cellDateAsString" />ID<s:property value="#request.cellActiviteId" /></s:set>
		<%		
		String  astreinteId = "" + ((Astreinte)ActionContext.getContext().getValueStack().findValue("astreinte")).getAstreinteId(); 
		%>		
		
		
		
		<a parsecontent="true" showerror="true" targets="<%=(ActionContext.getContext().getValueStack().findValue("divId"))%>"  href="<%=(ActionContext.getContext().getValueStack().findValue("deleteAction"))%>" id="widget_1765105650" ajaxaftervalidation="false" validate="false" dojotype="struts:BindAnchor">Supprimer</a>
		
		</li>
	</s:iterator>
</ul>