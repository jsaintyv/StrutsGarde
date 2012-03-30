<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <link href="<s:url value="/resources/main.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<center>
<div class="titleDiv"><s:text name="application.title"/></div>
<h1><s:text name="label.connexion"/></h1>
	 <table>
		<tr><td align="left" style="font:bold;color:red"> 
	            <s:fielderror/> 	 	
                <s:actionerror/>
                <s:actionmessage/></td></tr>
     </table>		 	
    <s:form action="connexion">
     <table align="center" class="borderAll">     	 
         <tr><td class="tdLabel"><s:text name="label.login"/></td>
         	        <td><s:textfield name="user.login" size="30"/></td>
         </tr>
        <tr>
            <td class="tdLabel"><s:text name="label.password"/></td>
                            <td><s:password name="user.passwordmd5"  size="30"/></td>
        </tr>
       
        <tr>
         
    		    <td><s:submit action="connexion" key="button.label.connexion" cssClass="butStnd"/></td>
    	  		<td></td>
    	</tr>
    </table> 		  		 
    	</s:form>
</center>		
</body>
</html>