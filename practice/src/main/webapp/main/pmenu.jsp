<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
<style>
.mainnav{
   background-color: #483D8B;
   list-style: none;
   color: #ffffff;
}

.mainnav:after{ /*container-snb,content  바로 붙지 않게 하기 위해서 clear 함 */
   content: '';
   display: block;
   clear: both;
}

.mainnav li{
   display: inline-block;
   justify-content: space-between;
}

.mainnav li a {
   display: block;
   padding: 0 13px; /*  위아래, 좌우 */
   background-color: #483D8B;
   color: #ffffff;
   font: bold 16px/40px 'Nanum Gothic', sans-serif; 
      /*폰트의 굵기 | 글자의 크기 | /line-height 줄간격 |  글꼴  , 앞에 글꼴없으면 다음 글꼴*/
   text-transform: uppercase;
   text-decoration: none;
}
.mainnav li a:hover {
   color: #660000;
   background-color: #ffcc00;
}
</style>    


</style>
</head>
<body>

</body>
</html>