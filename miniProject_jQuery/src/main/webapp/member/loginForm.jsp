
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form[name="loginForm"] div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
form[name="loginForm"] {
   display: flex;
   flex-direction: column;
   align-items: center;
   margin: 0 auto;
   text-align: left;
}
</style>
</head>
<body>

<form name="loginForm" id="loginForm">
	<a href="../index.jsp" style="text-decoration: none;">
		<h1>로그인</h1>
	</a>
 <table border="1" cellpadding="5" cellspacing="0">
   <tr>
  	<th >아이디</th>
  	<td>
  	 <input type="text" name="id" id="id" >
  	 <div id="idDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<th>비밀번호</th>
  	<td>
  	 <input type="password" name="pwd" id="pwd" >
  	 <div id="pwdDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<td colspan="2" align="center">
  	 <input type="button" value="로그인" id="loginBtn"> <!--id속성은 유일하게 하나일떄 class는 여러개   -->
	 <input type="button" value="회원가입" 
	 onclick="location.href='writeForm.do'">
  	</td>
  </tr>
 </table>
 
 <br><br>
 
 <div id="loginResult"></div> <!-- 아이디랑 비번 틀렸다는걸 여기에 띄우기위해 -->
</form>


<%-- jquery: 페이지 이동 없는 화면 전환 시 사용 --%>
<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<%-- jquery 사용 시, import 이후 작성 --%>
<script type="text/javascript">
$('#loginBtn').click(function(){ // 로그인 버튼 클릭 시
   $('#idDiv').empty();
   // document.getElementById("idDiv").innerText = "";
   
   $('#pwdDiv').empty();
   // document.getElementById("pwdDiv").innerText = "";
   
   if($('#id').val() == '') {
   // if(document.loginForm.id.value=="")
   
   $('#idDiv').text('아이디 입력');
   // document.getElementById("idDiv").innerText="아이디 입력";
   
   $('#id').focus();
   }

   else if($('#pwd').val() == '') {
   // else if(document.loginForm.pwd.value=="")
   
   $('#pwdDiv').text('비밀번호 입력');
   // document.getElementById("pwdDiv").innerText="비밀번호 입력";
   
   $('#pwd').focus();
   }
   
   else {
      $.ajax({ // {코드 작성 구간}
         type: 'post',
         // type: get or post method 선택
         // 문자: "" or ''(ajax에서 자주 사용) 사용 가능
         
         url: '/miniProject_jQuery/member/login.do',
         // url: action 지정 경로
         // *.do > controlServlet > LoginService.java > 메서드처럼 요청이 끝나면 호출한 ajax로 복귀

         data: 'id=' + $('#id').val() + '&pwd=' + $('#pwd').val(),
         // data: 서버로 보낼 데이터 기재('변수=값&변수=값')
         
         dataType: 'text',
         // dataType: 서버로부터 받을 자료형, text, xml, html, json (총 4개)
         
         success: function(result) {
         // result: url > java > jsp > jsp에서 return 받은 text
            result = result.trim(); // 공백 제거
            if(result == 'ok') { // 자바가 아니므로 equal 사용 X
            // result: url > java > jsp > jsp에서 return 받은 text가 'Okay'일 경우, 페이지 이동
               location.href='../index.jsp';
            } else if(result == 'fail') {
            // result: url > java > jsp > jsp에서 return 받은 string이 'Fail'일 경우, loginResult에 값 입력
               $('#loginResult').text('아이디 또는 비밀번호 불일치');
               $('#loginResult').css('font-size', '12pt');
            }
         },
         
         error: function(err) {
            console.log(err); // error msg 요청
         }
         
      });
      // $ ( )  $함수
      // $ { }  el tag: 화면 출력
      // 주석 처리하고 tag 작성 유의
   }
   
});

</script>
</body>
</html>






