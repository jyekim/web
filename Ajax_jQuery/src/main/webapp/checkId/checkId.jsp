<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>   <%--XML 태그 시작 전에 공백 제거  --%>    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>


<%-- DB연동 => 만약에 user_id가 hong이라면 이미 DB에 저장된 ID라고 인식(true) --%>
<c:set var="result" value="false" />
<c:if test="${param.user_id == 'hong' }">
	<c:set var="result" value="true" />
</c:if>

<%-- XML로 보내기  // xml은 앞에 공백이 있으면 안되는데 위에 써진것을 다 공백으로 인식하기에 에러떨어짐--%>
<?xml version="1.0" encoding="UTF-8"?>
<check_id>
	<result>${result }</result>
</check_id>
