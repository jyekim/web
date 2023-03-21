<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>${param.id}는 사용 가능</h3> 
	<input type ="button" value="사용하기" onclick="checkIdClose('${param.id}')">
<script type = "text/javascript">
function checkIdClose(id) { //열려있는 객체들 중에서 writeform id값을 넣어주는것 사용 가능한 아이디를 자동으로 넣어주는 것 
	opener.writeForm.id.value = id
	opener.writeForm.check.value = id	//중복 체크 버튼을 눌렀다는 확인용!
	window.close()
	opener.writeForm.pwd.focus() //패스워드로 넘어가게 하는 것 
}
</script>	
</body>
</html>