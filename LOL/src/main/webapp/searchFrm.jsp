<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Search Result</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style type="text/css">
    	h1 {
    		text-align: center;
    	}
    	
   #searchResult {
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
}

    </style>
</head>
<body>
<h1> 챔피언 검색 결과 </h1>
<br>
<!-- 검색 결과 -->
<div id="searchResult"></div>
</body>
<script>
    // 서버로부터 전달받은 JSON 데이터
    var searchResultData = ${searchResult};

    // 예시: 콘솔에 검색 결과 출력
    console.log(searchResultData);

    // 여기서부터는 검색 결과를 원하는 방식으로 활용
    var table = document.createElement("table");
    table.border = "1";

    // 테이블 헤더 추가
    var header = table.createTHead();
    var row = header.insertRow(0);
    for (var key in searchResultData[0]) {
        var th = document.createElement("th");
        th.innerHTML = key;
        row.appendChild(th);
    }

    // 테이블 바디 추가
    var body = table.createTBody();
    for (var i = 0; i < searchResultData.length; i++) {
        var row = body.insertRow(i);
        for (var key in searchResultData[i]) {
            var cell = row.insertCell();
            cell.innerHTML = searchResultData[i][key];
        }
    }

    // 결과를 특정 div에 추가
    var resultDiv = document.getElementById("searchResult");
    resultDiv.appendChild(table);

    console.log(resultDiv);
</script>
</html>
