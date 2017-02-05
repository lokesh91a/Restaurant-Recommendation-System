google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart1);
function drawChart1() {
	var data = google.visualization.arrayToDataTable([
			[ 'Training/Test', 'OFF', 'ON' ], [ '3500/400', 61.30, 69.85 ],
			[ '6500/900', 53.62, 71.30 ], [ '8500/600', 42.40, 51.92 ],
			[ '2000/150', 49.66, 55.67 ] ]);

	var options = {
		title : 'Feature Selector : Prediction Accuracy Performance',
		vAxis : {
			title : 'Accuracy'
		},
		hAxis : {
			title : 'Training/Test Datasets Size'
		},
		seriesType : 'bars',
		series : {
			5 : {
				type : 'line'
			}
		}
	};

	var chart1 = new google.visualization.ComboChart(document
			.getElementById('chart_div1'));
	chart1.draw(data, options);
}

google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart2);
function drawChart2() {
	var data2 = google.visualization.arrayToDataTable([
			[ 'Lower Cutoff', '6500', '8500' ], [ '-10', 53.62, 38.51 ],
			[ '-10', 53.62, 41.38 ], [ '-5', 53.62, 41.38 ],
			[ '0', 71.30, 41.38 ], [ '20', 67.19, 35.28 ],
			[ '50', 61.48, 33.37 ] ]);

	var options2 = {
		title : 'Chi-Square Lowest Score Selection',
		hAxis : {
			title : 'Cutoff Score',
			titleTextStyle : {
				color : '#333'
			}
		},
		vAxis : {
			minValue : 0
		}
	};

	var chart2 = new google.visualization.AreaChart(document
			.getElementById('chart_div2'));
	chart2.draw(data2, options2);
}

google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawPieChart1);
function drawPieChart1() {

	var pdata = google.visualization.arrayToDataTable([
			[ 'predicted_class', 'records' ], [ 'neg(correct)', 101 ],
			[ 'pos(incorrect)', 154 ], [ 'ntr(incorrect)', 44 ], ]);

	var poptions = {
		title : 'NEG classification @ pos (3.5 - 5), neg (0 - 2.5), ntr (2.5 - 3.5)'
	};

	var pchart = new google.visualization.PieChart(document
			.getElementById('pie_chart1'));
	pchart.draw(pdata, poptions);
}

google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawPieChart2);
function drawPieChart2() {

	var pdata = google.visualization.arrayToDataTable([
			[ 'predicted_class', 'records' ], [ 'neg(correct)', 101 ],
			[ 'pos(incorrect)', 154 ], [ 'ntr(incorrect)', 44 ], ]);

	var poptions = {
		title : 'NEG classification @ pos (3.5 - 5), neg (0 - 2.5), ntr (2.5 - 3.5)'
	};

	var pchart = new google.visualization.PieChart(document
			.getElementById('pie_chart2'));
	pchart.draw(pdata, poptions);
}


$(window).resize(function() {
	drawChart1();
	drawChart2();
	drawPieChart1();
	drawPieChart2();
});