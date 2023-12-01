function loadChampionList() {
	$.ajax({
		url: '/data',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log('Received data:', data);

			// 받아온 데이터로 챔피언 리스트 업데이트
			updateChampionList(data);

			// h1 태그의 텍스트 변경
			$('h1').text('챔피언 리스트');

			// 검색창 숨기기
			$('#searchForm').hide();
		},
		error: function(xhr, status, error) {
			console.error('Error loading JSON data:', status, error);
		}
	});
}

function updateChampionList(championList) {
	var champListDiv = $('#champList');
	var searchShowDiv = $('#searchShow');
	champListDiv.empty(); // 기존에 있던 내용 비우기
	console.log(championList.length);

	// 이미 생성된 searchLink가 있다면 삭제
	searchShowDiv.empty();

	// 리스트 위에 새로운 링크를 생성
	var searchLink = $('<a>').attr('href', '#').text('검색창 열기').on(
		'click', function() {
			// 이미 검색창이 열려있는 경우 아무 동작 안함
			if ($('#searchForm').is(':visible')) {
				return;
			}

			// 검색창 보이기
			$('#searchForm').show();
			// 리스트 숨기기
			champListDiv.hide();
			// h1 태그의 텍스트 변경
			$('h1').text('LOL TeamProject');

			// 링크 숨기기
			searchLink.hide();
		});


	// h1 태그 다음에 searchLink 추가
	$('h1').after(searchLink);

	// searchLink에 스타일 추가
	searchLink.css({
		'display': 'block',
		'margin-top': '20px' // 조절할 margin-top 값
	});

	// 검색창 링크를 리스트에 추가
	searchShowDiv.append(searchLink);

	// 리스트에 링크를 클릭했을 때 검색창 숨기기
	champListDiv.on('click', 'a', function() {
		// 검색창 숨기기
		$('#searchForm').hide();
		// 리스트 보이기
		champListDiv.show();
		// h1 태그의 텍스트 변경
		$('h1').text('챔피언 리스트');

		// 링크 보이기
		searchLink.show();
	});

	// 챔피언 리스트를 반복하면서 div에 추가		
	for (var i = 0; i < championList.length; i++) {
		var champion = championList[i];

		// 각 챔피언을 나타낼 div 생성
		var championDiv = $('<div>').addClass('champion-item'); // 클래스 추가

		// 챔피언 이미지를 나타내는 img 태그 생성
		var championImg = $('<img>').attr('alt', 'Champion').attr(
			'src', 'img/' + champion + '.jpg');

		// 이미지를 감싸는 링크를 생성
		var championLink = $('<a>').attr('href', '/kda?champName=' + champion); //your-servlet-url'에는 실제 서블릿의 URL이 들어가야 함
		championLink.append(championImg);

		//				// 챔피언 이름 태그 생성
		//				var championName = $('<p>').text(champion).attr('name', 'champName');

		// 생성한 요소들을 div에 추가
		championDiv.append(championLink);
		//				championDiv.append(championName);

		// div를 리스트에 추가
		champListDiv.append(championDiv);
	}

	// 리스트 다시 표시
	champListDiv.show();
}