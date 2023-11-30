<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Team</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
.search-box{

}

</style>
</head>
<body>
	<div>
		
		<nav>
			<ul id="asyncNav">
				<div class="image-container">
					<li><a href="#" onclick="loadChampionList()">챔피언 리스트</a></li>
					<li><a href="#" onclick="login()">사이트 로그인</a></li>
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
	</div>

	<div id="searchShow"></div>

	<div id="champList"></div>

	<script src="js/index.js"></script>
</body>
</html>