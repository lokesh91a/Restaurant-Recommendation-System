<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta
	name="RESTful WebServices for Foodie : Restaurant Recommendation Tool"
	content="">
<meta name="Anurag Malik, Lokesh Agrawal" content="">

<title>Foodie API</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet" />

<!-- Custom CSS -->
<link href="css/stylish-portfolio.css" rel="stylesheet" />
<link href="css/charts.css" rel="stylesheet" />

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css" />

<script src="js/jquery.js"></script>
<script>
	$(document).ready(function() {
		$(".result").hide();
		/* $(".input").focus(function() {
			$(".result").slideUp(1000);
		}); */

		$(".loc_btn").click(function() {
			findByLoc($(".loc_text").val());
		});
		$(".bus_btn").click(function() {
			findByBusiness($(".bus_text").val());
		});
		$(".rev_btn").click(function() {
			predictReview($(".rev_text").val().replace(/\//g, ""));
		});

		$('.loc_results').on('click', '.res_item', function() {
			$(".bus_text").val($('#bus_id', this).val());
		});
	});
</script>
<script>
	function findByLoc(loc) {
		$(".loc_results").html(
				"<i class='fa fa-spinner fa-spin fa-3x fa-fw margin-bottom'></i>"
						+ "<span class='sr-only'>Loading...</span>");
		$(".loc_results").show();
		$
				.ajax({
					type : 'GET',
					url : 'http://129.21.110.238:8080/foodie/kpt/location/'
							+ loc,
					dataType : 'json',
					crossDomain : true,
					success : function(data, textStatus, jqXHR) {
						var _len = data.length;
						var post, i, str = "", res = "";
						var itemHTML = "";
						for (i = 0; i < _len; i++) {
							post = data[i];
							itemHTML = [
									"<div class=\"res_item col-sm-4 col-md-4\" >",
									"<a class=\"thumbnail\">",
									"<img src=\"img/rest"
											+ (i + 1)
											% 6
											+ ".jpg\" onclick = 'resItem();' alt=\"...\">",
									"<div class=\"caption\">",
									"<h3>",
									post.name,
									"</h3>",
									"<p>",
									"Foodie : " + post.frating,
									"<br>",
									post.address,
									"</p>",
									"<input class=\"bus_id\" type=\"hidden\" id=\"bus_id\" value='"+ post.businessID +"'/>" ]
									.join('')
							for (j = 1; j <= post.frating; j++) {
								itemHTML += " <span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>"
							}
							itemHTML += [ "</div>", "</a>", "</div>" ].join('')
							str += itemHTML;
							if ((i + 1) % 2 == 0) {
								res += "<div class=\"row\">" + str + "</div>";
								str = "";
							}
						}
						$(".loc_results").html(res);
						//$(".loc_text").val("");
					}
				});
	};
</script>
<script>
	function findByBusiness(bus) {
		$(".bus_results").html(
				"<i class='fa fa-spinner fa-spin fa-3x fa-fw margin-bottom'></i>"
						+ "<span class='sr-only'>Loading...</span>");
		$(".bus_results").show();
		$
				.ajax({
					type : 'GET',
					url : 'http://129.21.110.238:8080/foodie/kpt/business/'
							+ bus,
					dataType : 'json',
					crossDomain : true,
					success : function(data, textStatus, jqXHR) {

						var _len = data.length;
						var post, i, str = "";
						var itemHTML = "";
						for (i = 0; i < _len; i++) {
							post = data[i];
							itemHTML = [
									"<div class='panel panel-success result bus_results'>",
									"<div class='panel-heading'>"
											+ post.sentiment + "</div>",
									"<div class='panel-body'>" + post.text
											+ "</div>", "</div>"

							].join('')
							str += itemHTML;
						}
						$(".bus_results").html(str);
						$(".bus_results").slideDown(1500);
						//$(".bus_text").val("");
					}
				});
	};
</script>
<script>
	function predictReview(review) {
		$(".rev_results").html(
				"<i class='fa fa-spinner fa-spin fa-3x fa-fw margin-bottom'></i>"
						+ "<span class='sr-only'>Loading...</span>");
		$(".rev_results").show();
		$
				.ajax({
					type : 'GET',
					url : 'http://129.21.110.238:8080/foodie/kpt/predict/'
							+ review,
					dataType : 'text',
					crossDomain : true,
					success : function(data, textStatus) {
						var itemHTML = "";
						if (data == "pos") {
							itemHTML = "<div class='alert alert-success result rev_results' role='alert'>"
									+ "<h3 class> <span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'>"
									+ "</span> </h3>Heads Up! Foodie Sentiment Analysis engine calculated this review to be POSITIVE.	</div>";
						} else if (data == "neg") {
							itemHTML = "<div class='alert alert-warning result rev_results' role='alert'>"
									+ "<h3 class> <span class='glyphicon glyphicon-thumbs-down' aria-hidden='true'>"
									+ "</span> </h3>Whoops! Foodie Sentiment Analysis engine calculated this review to be NEGATIVE.	</div>";
						} else {
							itemHTML = "<div class='alert alert-success result rev_results' role='alert'>"
									+ "<h3 class> <span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'>"
									+ "</span> </h3>Not bad! Foodie Sentiment Analysis engine calculated this review to be NEUTRAL.	</div>"
						}
						$(".rev_results").html(itemHTML);
						$(".rev_results").slideDown(1500);
						$(".rev_text").val("");
					}
				});
	};
</script>

</head>
<body>
	<!-- Callout -->
	<aside class="callout">
		<div class="text-vertical-center">
			<h1>RESTful Services</h1>
		</div>
	</aside>

	<div class="json_data"></div>
	<section id="about" class="about">
		<div class="container">
			<div class="row" id="loc_rest">
				<div class="col-lg-12 text-center">
					<h2>
						LOCATION <span class="glyphicon glyphicon-globe"
							aria-hidden="true"></span>
					</h2>
					<p class="lead">
						<!-- <label for="basic-url">GET</label> -->
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon3">GET/location/</span>
						<input type="text" class="form-control loc_text input"
							id="basic-url" placeholder="...treat yourself"
							aria-describedby="basic-addon3" /> <span class="input-group-btn">
							<button class="btn btn-default loc_btn" type="button">Go!</button>
						</span>
					</div>
					</p>

					<p class="loc_result">
						<div class="list-group loc_results result">
							<!-- PLACE FOR LOCATION RESULTS -->
						</div>
					</p>

				</div>
			</div>

			<!-- /.row -->

			<div class="row" id="business">
				<div class="business col-lg-12 text-center">
					<h2>
						BUSINESS <span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
					</h2>
					<p class="lead">
						<!-- <label for="basic-url">GET</label> -->
					<div class="input-group input">
						<span class="input-group-addon" id="basic-addon3">GET/business/</span>
						<input type="text" class="form-control input bus_text"
							placeholder="...review your favourite restaurant." /> <span
							class="input-group-btn">
							<button class="btn btn-default bus_btn" type="button">Go!</button>
						</span>
					</div>
					</p>

					<p class="bus_results">
						<!-- PLACE FOR BUSINESS RESULTS -->
					</p>

				</div>
			</div>
			<!-- row -->

			<div class="row" id="rev_rest">
				<div class="col-lg-12 text-center">
					<h2>
						REVIEW <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
					</h2>
					<p class="lead">
						<!-- <label for="basic-url">GET</label> -->
					<div class="input-group input">
						<span class="input-group-addon" id="basic-addon3">GET/predict/</span>
						<input type="text" class="form-control input rev_text"
							id="basic-url" placeholder="...mind your sentiments"
							aria-describedby="basic-addon3" value="" /> <span
							class="input-group-btn">
							<button class="btn btn-default rev_btn" type="button">Go!</button>
						</span>
					</div>
					</p>

					<p class="rev_results">
						<!-- PLACE FOR REVIEW RESULT -->
						<!-- <div class="alert alert-success result rev_results" role="alert">
						<h3 class>
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
						</h3>
						Heads Up! Foodie Sentiment Analysis engine calculated this review
						to be positive.
					</div> -->
					</p>

				</div>
			</div>
			<!-- row -->
		</div>
		<!-- /.container -->
	</section>
</body>
</html>