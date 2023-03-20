<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="angel" uri="tld" %> <!--prefix이 파일 태그라는 게 있다는 것 uri는 파일의 위치정보 알려줌 이 파일을 참조하라는 뜻   -->   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>자바클래스의 메소드를 이용</h3>
${param['x']} + ${param['y'] }= ${ angel:sum(param['x'], param['y']) }<br><br>

${param.x} * ${param.y }= ${ angel:mul(param.x, param.y) }<br><br>


</body>
</html>