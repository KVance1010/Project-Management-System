var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for (var i=0; i <arrayLength; i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}



const data = {
    labels: labelData,
    datasets: [{
       label: 'My First dataset',
       backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
       data: numericData,
    }]
};

const config = {
   type: 'pie',
   data: data,
   options: {
	title: {
		display: true,
		text: 'Project Statuses'
		
	}
}
};
  
const myChart = new Chart(
   document.getElementById('myPieChart'),
   config
);

// [{"value": 1, "label":"completed"}, {"value": 2, "label":"not-started"},{"value": 1, "label":"inprogress"}]
function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}