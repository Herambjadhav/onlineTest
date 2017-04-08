<html>
<Head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</Head>
<Body>
	<div class="page-header">
		<h1 id="header" style="text-align: center;">Welcome to Online
			Test Application</h1>
	</div>
	<div class="container" id="login_div">
		<div class="form-group">
			<label for="name">Name:</label> <input type="text"
				class="form-control" id="name">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email"
				class="form-control" id="email">
		</div>
		<button id="login" class="btn btn-default">Submit</button>
	</div>

	<div class="container" id="afterLogin">
		<div class="form-group">
			<label for="topic">Select Topic:</label> <select class="form-control"
				id="topic">
				<option>Economics</option>
				<option>History</option>
				<option>Science</option>
			</select> <label for="testId">Select Test:</label> <select
				class="form-control" id="testId">
				<option>Test1</option>
				<option>Test2</option>
			</select> <br>
			<button id="start" class="btn btn-default">Start Test</button>
			<button id="review" disabled class="btn btn-default">Review
				Tests</button>
		</div>
	</div>

	<div class="container">
		<div class="form-group" id="review_div" style="margin: 20"></div>
	</div>

	<div class="container" id="question_space">
		<br>
		<div id="myCarousel" class="carousel" data-ride="carousel"
			data-interval="false">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
				<li data-target="#myCarousel" data-slide-to="4"></li>
				<li data-target="#myCarousel" data-slide-to="5"></li>
				<li data-target="#myCarousel" data-slide-to="6"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox" style="padding: 80;">

				<div class="item active" id="Q0">
					<h2>Q1</h2>
				</div>

				<div class="item" id="Q1">
					<h2>Q1</h2>
				</div>


				<div class="item" id="Q2">
					<h2>Q2</h2>
				</div>

				<div class="item" id="Q3">
					<h2>Q3</h2>
				</div>

				<div class="item" id="Q4">
					<h2>Q4</h2>
				</div>

				<div class="item" id="Q5">
					<h2>Q5</h2>
				</div>

				<div class="item" id="Q6">
					<h2>Q6</h2>
				</div>

				<div class="item" id="Q7">
					<h2>Q6</h2>
				</div>

			</div>

			<button class="btn btn-primary" href="#myCarousel" data-slide="prev"
				style="float: left;margin-bottom: 20;">Previous</button>
			<button class="btn btn-primary" href="#myCarousel" data-slide="next"
				style="float: right;margin-bottom: 20;">Next</button>
		</div>
	</div>

</Body>
<script>
	$( document ).ready(function() {
		var questions;
		var userData = new Object();
		
		$("#afterLogin").hide();
		$("#question_space").hide();
		
		$("#login").click(function(){
			userData.emailId =  $("#email").val();
			
			$.get( "user/getUser/"+$("#email").val()+"/"+$("#name").val(), function( data, status ) {
				if(data == "EXISTING"){
			  		$("#review").attr("disabled", false);
			  	}
			});
			$("#header").html("Hi, "+$("#name").val());
			$("#login_div").hide();
	    	$("#afterLogin").show();
    	});
		
		$("#start").click(function(){
			$('#review_div').hide();
			userData.topic = $("#topic").val();
			userData.test = $("#testId").val();
				
			$.getJSON( "services/"+$("#topic").val()+"/"+$("#testId").val()+"/getQuestions", function( data ) {
				questions = data;
				var k = 0;
				$.each( data, function( i, obj ) {
				    console.log(obj.questionId);
				    $("#Q"+k).empty();
				    $("#Q"+k).append("<H4>Question : "+(k+1)+"</p");
				    $("#Q"+k).append("<p>"+obj.question+"</p");
				    $("#Q"+k).append("<div class='radio'>");
				    var i = 1;
				    $.each( obj.options, function( j, option ){
				    	$("#Q"+k).append("<br><label><input type='radio' value="+i+"_"+obj.questionId+" name='options"+k+"'>"+option+"</label></div>");
				    	i++;
				    });
				    if(k == 7){
				    	$("#Q"+k).append("<br><button class='btn btn-primary' id='submit_ans'>Submit</button>");
				    }
				    $("#Q"+k).append("</div>");
				    k++;
			  	});  
			});
			$("#question_space").show();
    	});
		
		$('#Q7').on('click', '#submit_ans', function() {
		    var answers = [];
		    var score = 0;
			for(k = 0; k < 8; k++){
		    	var answer = new Object();
				var str = $("input[name=options"+k+"]:checked").val()
				var arr = str.split("_");
		    	answer.option = arr[0];
		    	answer.question_id = arr[1];
		    	$.each(questions, function(i, obj ){
		    		if(answer.question_id == obj.questionId && answer.option == obj.correctOption){
		    			score ++;
		    		} 
		    	});
		    	answers.push(answer);
		    	console.log($("input[name=options"+k+"]:checked").val());
			}
			
			userData.score = score;
			userData.userAnswers = answers;
			
			console.log(JSON.stringify(userData));
			// Save answers
			
			$.ajax({
			    url: 'user/saveUserData',
			    type: 'POST',
			    data: JSON.stringify(userData),
			    contentType: 'application/json; charset=utf-8',
			    dataType: JSON.stringify(userData),
			    success: function(msg) {
			        console.log("Data Saved!");
			    }
			});
			
			$("#question_space").hide();
			$("#review").attr("disabled", false);
			alert("your score is : "+score);
		});
		var reviewData;
		
		$("#review").click(function(){
			$("#question_space").hide();
			$.getJSON( "user/"+userData.emailId+"/reviewData", function( data ) {
				reviewData = data;
				$("#review_div").empty();
				$.each( data, function( i, obj ) {
					$("#review_div").append("<br><div class='radio'><input type='radio' value="+obj.id+" name='review_list' ><label>Date: <label>"+obj.timestamp+" <label>Topic: <label>"+obj.topic+" <label>Test: <label>"+obj.test+"<label>Score: <label>"+obj.score+"</label></div>");
				});	
				$("#review_div").append("<br><button class='btn btn-primary' id='load'>View Questions</button>");
			});
			$("#review_div").show();
		});
		
		$('#review_div').on('click', '#load', function() {
			var id = $("input[name=review_list]:checked").val();
			$.each( reviewData, function( i, obj ) {
				if(obj.id == id){
					var k = 0;
					$.each( obj.questionSet, function( i, obj ) {
					    $("#Q"+k).empty();
					    $("#Q"+k).append("<H4>Question : "+(k+1)+"</p");
					    $("#Q"+k).append("<p>"+obj.question+"</p");
					    $("#Q"+k).append("<div class='radio'>");
					    var i = 1;
					    $.each( obj.options, function( j, option ){
					    	if(i == obj.selectedOption){
					    		$("#Q"+k).append("<br><label><input type='radio' value="+i+"_"+obj.questionId+" name='options"+k+"' checked disabled>"+option+"</label></div>");
					    	} else{
					    		$("#Q"+k).append("<br><label><input type='radio' value="+i+"_"+obj.questionId+" name='options"+k+"' disabled>"+option+"</label></div>");	
					    	}
					    	
					    	i++;
					    });
					    if(k == 7){
					    	$("#Q"+k).append("<br><button class='btn btn-primary' id='close'>Close</button>");
					    }
					    $("#Q"+k).append("</div>");
					    k++;
				  	});  
				$("#question_space").show();
				}
			});	
		});
		
		$('#Q7').on('click', '#close', function() {
			$("#question_space").hide();
		});
		
	});
	
	</script>

</html>