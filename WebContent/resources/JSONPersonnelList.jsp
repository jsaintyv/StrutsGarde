<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<table align=center class="borderAll">
	<tr>
		<th><s:text name="label.firstName" />
		</th>
		<th><s:text name="label.lastName" />
		</th>
		<th><s:text name="label.phone" />
		</th>
		<th><s:text name="label.phoneCellular" />
		</th>
		<th><s:text name="label.phoneWork" />
		</th>
		<th><s:text name="label.comment" />
		</th>
	</tr>

	<s:iterator value="listPersonnel" status="status">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			<td class="nowrap"><s:url id="loadPersonnel"
					action="loadPersonnel">
					<s:param name="personnelIdToLoad" value="personnelId" />
				</s:url> <s:a href="%{loadPersonnel}">
					<s:property value="nom" />
				</s:a></td>
		<td class="nowrap"><s:property value="telPerso" /></td>		
		<td class="nowrap"><s:property value="telMobile" /></td>
		<td class="nowrap"><s:property value="telPro" /></td>
			
		</tr>
	</s:iterator>
</table>