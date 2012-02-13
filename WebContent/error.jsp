<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isErrorPage="true" %>	
<html>
<head>
    <title>Error Page</title>
    <link href="resources/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="titleDiv">Error</div>	
<p>
<font size=4 color=orange>Your are seeing the error page</font><br><br>
In order that the development team can address this error, please report what you were doing that caused this error.
<br/><br/>
The following information can help the development
team find where the error happened and what can be done to prevent it from
happening in the future.
<br/>
<%
if(null == exception){
    exception = (Throwable)request.getAttribute("org.apache.struts.action.EXCEPTION");
}
%>
<pre style="font-size:12px"><%
if(null == exception){
    out.write("Source of error is unknown.");
}else{
    java.io.StringWriter sw = new java.io.StringWriter();
    java.io.PrintWriter pw = new java.io.PrintWriter(sw);

    exception.printStackTrace(pw);
    out.write(sw.getBuffer().toString());

}
%></pre>
</body>
</html>
