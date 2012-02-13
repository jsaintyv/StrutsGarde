<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
[
["",""]
<s:iterator value="listActivite" status="status">
,["<s:property value="nom"/>","<s:property value="activiteId"/>"]
</s:iterator>]