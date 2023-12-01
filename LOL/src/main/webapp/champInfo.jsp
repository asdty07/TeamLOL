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
        text-align: center; /* ��ü ���� ��� ���� */
    }

    table {
        width: 40%;
        margin: 20px auto; /* ǥ�� ���� �߾� ���� */
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #dddddd;
        text-align: center; /* ǥ �� ��� ���� */
        padding: 8px;
    }

    th {
        background-color: #f2f2f2;
    }

    b {
        color: #333; /* ���̺� ���� ���� */
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
	    // �������� �ε�Ǹ� ����� �ڵ�
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
	                { label: "�̸�", value: champName },
	                { label: "��� KDA", value: champKda["kda"] },
	                { label: "�ȷ�", value: data }
	            ];

	            for (var i = 0; i < dataRows.length; i++) {
	                if (dataRows[i].value === undefined || dataRows[i].value === null) {
	                    alert(dataRows[i].label + ' ���� 0�Դϴ�.');
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


	            // �·� �߰�
	            var winRateRow = table.insertRow(dataRows.length);
	            var winRateLabelCell = winRateRow.insertCell(0);
	            var winRateValueCell = winRateRow.insertCell(1);
	            winRateLabelCell.innerHTML = "<b>�·�</b>";

	            // ���ο� URL('/wnis')�� �����͸� ��û
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

	            // ���̺��� body�� �߰�
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
