<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Kda</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<h1>KDA</h1>
</body>
	<script type="text/javascript">
		var champKda = ${champKda};
		var champName = '<%= request.getAttribute("name") %>';
		
		console.log(champName);
		console.log(name);
		
		 var table = document.createElement("table");
		 table.border = "1";
		 
		 var data = [
	            { label: "이름", value: champName },
	            { label: "평균 KDA", value: champKda["kda"] }
	            // ... 필요한 만큼 변수 추가
	        ];
		 
		 for (var i = 0; i < data.length; i++) {
	            var row = table.insertRow(i);
	            var labelCell = row.insertCell(0);
	            var valueCell = row.insertCell(1);
	            labelCell.innerHTML = "<b>" + data[i].label + "</b>";
	            valueCell.innerHTML = data[i].value;
	        }

	        // 테이블을 body에 추가
	        document.body.appendChild(table);
	</script>
</html>