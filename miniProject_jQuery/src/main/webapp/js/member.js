function select(){
	document.writeForm.email2.value = document.writeForm.email3.value;
}


$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();

if( $('#name').val() == '' ) {
		$('#nameDiv').text('이름 입력');
		$('#name').focus(); 
	}	
	else if($('#id').val() == '') {
		$('#idDiv').text('아이디 입력');
		$('#id').focus(); 
	}
	else if($('#pwd').val() == '') {
	$('#pwdDiv').text('비밀번호 입력');
	$('#pwd').focus(); 
	}
	else if($('#pwd').val() != $('#repwd').val()) {
	$('#pwdDiv').text('비밀번호가 맞지 않습니다.');
	$('#pwd').focus(); 
	}
	else if($('#id').val()!= $('#check').val()){
	$('#idDiv').text('중복체크 하세요');
	$('#id').focus(); 
	}
	else{
		//$('writeForm').submit();   
		/*여기서 submit 하면 action을 따라가니 writeform action새 페이지로 이동함*/
		//또는 
		
		//<form> 안에 있는 데이커 값을 문자열 형식으로 가져온다. 
		//alert($'#writeForm').serialize());
		$.ajax({
			type: 'post',
			url:'/miniProject_jQuery/member/write.do',
			data: $('#writeForm').serialize(),
			dataType: 'text',
			success: function(data){
				data = data.trim();
				
				if(data == "ok"){
					alert('회원가입을 축하합니다');
					 location.href = '../index.jsp';
				}else
					alert('회원가입 실패');
			},
			error: function(err){
				console.log(err);
				
			}
		}); // ajax
	} // else
});




function checkWrite(){/*writeform창에서 회원가입 이름 비번 중복체크까지 차례로 확인*/
	//if(document.writeForm.name.value == "") alert("이름 입력하세요")
	//if(document.getElementById("name").value == "") alert("이름 입력하세요")
	
	/*
	if(document.getElementById("name").value == "")
		document.getElementById("nameDiv").innerText="이름 입력";
	else if(document.getElementById("id").value == "")
		document.getElementById("idDiv").innerText="아이디 입력";
	else if(document.getElementById("pwd").value == "")
		document.getElementById("pwdDiv").innerText="비밀번호 입력";
	else if(document.getElementById("pwd").value != document.getElementById("repwd").value)
		document.getElementById("pwdDiv").innerText="비밀번호가 맞지 않습니다";
	else if(document.getElementById("id").value != document.getElementById("check").value)
		document.getElementById("idDiv").innertext="중복체크 하세요";
	else
		document.writeForm.submit();
}*/

/* 다음 우편번호 */
function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

       
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }
   }