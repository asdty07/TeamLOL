<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Team</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div>
		<nav>
			<ul id="asyncNav">
				<li><a href="#" onclick="loadChampionList()">챔피언 리스트</a></li>
				<li><a id="loginLink" href="loginFrm">사이트 로그인</a></li>
				<li><a id="logoutLink" href="logout">로그아웃</a></li>
			</ul>
		</nav>
	</div>

	<header>
		<h1>LOL TeamProject</h1>
	</header>

	<div class="search-box">
		<form action="search" method="post" id="searchForm">
			<input type="text" id="search" name="search" placeholder="검색할 챔피언">
			<button type="submit">
				<i class="fa fa-search"><img src="img/search.png"
					alt="Search Image"></i>
			</button>
		</form>
	</div>


	<div id="searchShow"></div>

	<div id="champList"></div>

	<script src="js/index.js"></script>

	<script type="text/javascript">
	    var id = '${id}'  
		console.log(id); //'aa', ''

		if (id === '') {
			$('#loginLink').show();
			$('#logoutLink').hide();
		} else {
			$('#loginLink').hide();
			$('#logoutLink').show();
		}
	</script>
</body>
</html>