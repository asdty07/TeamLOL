<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team</title>
<style type="text/css">
html {
	background: #1f80aa;
}

body {
	width: 300px;
	margin: 0 auto;
	padding-top: 8rem;
}

#asyncNav {
	list-style: none;
	padding: auto;
	margin: auto;
	background-color: #333;
	overflow: hidden;
}

#asyncNav li {
	float: left;
}

#asyncNav a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

#asyncNav a:hover {
	background-color: #ddd;
	color: black;
}

fieldset {
	position: relative;
	display: inline-block;
	padding: 0 0 0 40px;
	background: #fff;
	border: none;
	border-radius: 5px;
}

input, button {
	position: relative;
	width: 220px; /* 길이 조절 */
	height: 50px;
	padding: 0;
	display: inline-block;
	float: left;
}

input {
	color: #666;
	z-index: 2;
	border: 0 none;
}

input:focus {
	outline: 0 none;
}

input:focus+button {
	transform: translate(0, 0);
	transition-duration: 0.3s;
}

input:focus+button .fa {
	transform: translate(0px, 0);
	transition-duration: 0.3s;
	color: #fff;
}

button {
	z-index: 1;
	width: 50px;
	border: none;
	background: #ceb980;
	cursor: pointer;
	border-radius: 0 5px 5px 0;
	transform: translate(-50px, 0);
	transition-duration: 0.3s;
}

button img {
	width: 80%;
	height: auto;
	justify-content: center;
}

.fa-search {
	font-size: 1.4rem;
	color: #29abe2;
	z-index: 3;
	top: 25%;
	transform: translate(-190px, 0);
	transition-duration: 0.3s;
	transition: all 0.1s ease-in-out;
}

 #champList {
        margin-top: 50px; /* 원하는 간격으로 조절 */
    }
</style>
<script src="http://code.jquery.com/jquery-1.11.1.js"
	type="text/javascript"></script>
</head>
<body>
	<h1>LOL TeamProject</h1>
	<div>
		<nav>
			<ul id="asyncNav">
				<li><a href="#" onclick="loadChampionList()">챔피언 리스트</a></li>
				<li><a onclick="loadWinRate()">챔피언 승률</a></li>
				<li><a onclick="loadPickRate()">챔피언 픽률</a></li>
			</ul>
		</nav>
	</div>

	<div>
		<form action="search" method="post">
			<input type="text" id="search" name="search" placeholder="검색할 챔피언">
			<button type="submit">
				<i class="fa fa-search"><img src="img/search.png"
					alt="Search Image"></i>
			</button>
		</form>
	</div>

	<div id="champList"></div>
	
	

	<script>
		// 페이지 로드 시 실행
// 		$(document).ready(function() {
// 			// 페이지 로드 시 초기 데이터 로딩
// 			loadChampionList();
// 		});

		function loadChampionList() {
			$.ajax({
				url : '/data',
				method : 'GET',
				dataType : 'json',
				success : function(data) {
					console.log('Received data:', data);

					// 받아온 데이터로 챔피언 리스트 업데이트
					updateChampionList(data);
				},
				error : function(xhr, status, error) {
					console.error('Error loading JSON data:', status, error);
				}
			});
		}

		function updateChampionList(championList) {
			var champListDiv = $('#champList');
			champListDiv.empty(); // 기존에 있던 내용 비우기
			console.log(championList.length);
			
			// 챔피언 리스트를 반복하면서 div에 추가
			for (var i = 0; i < championList.length; i++) {
				var champion = championList[i];

				// 각 챔피언을 div에 추가
				var championDiv = $('<div>').text(champion);
				champListDiv.append(championDiv);
			}
		}

		function loadWinRate() {
			// 챔피언 승률을 로드하는 비동기 함수 호출
			// 예: $.ajax 또는 $.get 함수를 사용하여 서버에서 데이터를 가져와서 content 영역에 표시
		}

		function loadPickRate() {
			// 챔피언 픽률을 로드하는 비동기 함수 호출
			// 예: $.ajax 또는 $.get 함수를 사용하여 서버에서 데이터를 가져와서 content 영역에 표시
		}
	</script>
</body>
</html>