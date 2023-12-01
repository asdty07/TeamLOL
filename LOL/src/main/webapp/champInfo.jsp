<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>champInfo</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
    body {
        text-align: center; /* 전체 내용 가운데 정렬 */
    }

    table {
        width: 40%;
        margin: 20px auto; /* 표를 수평 중앙 정렬 */
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #dddddd;
        text-align: center; /* 표 셀 가운데 정렬 */
        padding: 8px;
    }

    th {
        background-color: #f2f2f2;
    }

    b {
        color: #333; /* 레이블 색상 지정 */
    }
</style>
</head>
<body>
	<h1>CHAMP INFO</h1>


	<script type="text/javascript">
	var champKda = ${champKda};
	var champName = '<%= request.getAttribute("name") %>';
	console.log(champName);
	console.log(name);
	$(document).ready(function() {
	    // 페이지가 로드되면 실행될 코드
	    $.ajax({
	        url: '/pick',
	        method: 'GET',
	        dataType: 'json',
	        data: { pick: '<%= request.getAttribute("name")%>' },
	        success: function(data) {
	            console.log('Received data:', data);

	            var table = document.createElement("table");
	            table.border = "1";

	            var dataRows = [
	                { label: "이름", value: champName },
	                { label: "평균 KDA", value: champKda["kda"] },
	                { label: "픽률", value: data }
	            ];

	            for (var i = 0; i < dataRows.length; i++) {
	                if (dataRows[i].value === undefined || dataRows[i].value === null) {
	                    alert(dataRows[i].label + ' 값이 0입니다.');
	                    window.history.back();
	                    return;
	                } else {
	                    var row = table.insertRow(i);
	                    var labelCell = row.insertCell(0);
	                    var valueCell = row.insertCell(1);
	                    labelCell.innerHTML = "<b>" + dataRows[i].label + "</b>";
	                    valueCell.innerHTML = dataRows[i].value;
	                }
	            }


	            // 승률 추가
	            var winRateRow = table.insertRow(dataRows.length);
	            var winRateLabelCell = winRateRow.insertCell(0);
	            var winRateValueCell = winRateRow.insertCell(1);
	            winRateLabelCell.innerHTML = "<b>승률</b>";

	            // 새로운 URL('/wnis')로 데이터를 요청
	            $.ajax({
	                url: '/wins',
	                method: 'GET',
	                dataType: 'json',
	                data: { wins: '<%= request.getAttribute("name")%>' },
	                success: function(wins) {
	                    console.log('Received data from /wnis:', wins);
	                    winRateValueCell.innerHTML = wins;
	                },
	                error: function(xhr, status, error) {
	                    console.error('Error loading JSON data from /wnis:', status, error);
	                }
	            });

	            // 테이블을 body에 추가
	            document.body.appendChild(table);
	        },
	        error: function(xhr, status, error) {
	            console.error('Error loading JSON data:', status, error);
	        }
	    });
	});

	</script>
</body>
</html>
