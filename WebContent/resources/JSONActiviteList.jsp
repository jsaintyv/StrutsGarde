<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<table align=center class="borderAll">
	<tr>
		<th><s:text name="label.activite" />
		</th>
		<th><s:text name="label.astreinte" />
		</th>
		<th><s:text name="label.delete" /></th>
	</tr>

	<s:iterator value="listActivite" status="status" var="activite">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			<td class="nowrap"><s:url id="loadActivite"
					action="loadActivite">
					<s:param name="activite.activiteId" value="activiteId" />
				</s:url> <s:a href="%{loadActivite}">
					<s:property value="nom" />
				</s:a></td>
			<td>
				<s:property value="comment"/>				
				<table class='activiteAstreinte'>
				<s:iterator value="%{getObtenirPour(activiteId)}" var="astreinte" status="status2">
					<tr class="<s:if test="#status2.even">astreinteeven</s:if><s:else>astreinteodd</s:else>">					
						<td class='perNom'><s:property value="#astreinte.personnel.nom" /> <s:property value="#astreinte.personnel.prenom" />&nbsp;</td>
						<td class='perTelPerso'><s:property value="#astreinte.personnel.telPerso" />&nbsp;</td>
						<td class='perTelMobile'><s:property value="#astreinte.personnel.telMobile" />&nbsp;</td>
						<td class='perTelPro'><s:property value="#astreinte.personnel.telPro" />&nbsp;</td>
						
						<td class='astreinteDe'>De <s:property value="%{getInHour(debut)}" />&nbsp;</td>
						<td class='astreinteA'>A <s:property value="%{getInHour(fin)}" />&nbsp;</td>				
					</tr>
				</s:iterator>
				</table>
			</td>
			<td>
				<s:url id="deleteActivite"
					action="deleteActivite">
					<s:param name="activiteIdToHandle" value="activiteId" />
				</s:url>
				<s:a href="%{deleteActivite}">
					<s:text name="label.delete"></s:text>
				</s:a>
			</td>
		</tr>
	</s:iterator>
</table>