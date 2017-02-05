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
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Navigation -->
	<a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i
		class="fa fa-bars"></i></a>
	<nav id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<a id="menu-close" href="#"
				class="btn btn-light btn-lg pull-right toggle"><i
				class="fa fa-times"></i></a>
			<li class="sidebar-brand"><a href="#top" onclick=$("#menu-close").click();>Foodie</a>
			</li>
			<li><a href="#top" onclick=$("#menu-close").click(); >Home</a></li>
			<li><a href="#about" onclick=$("#menu-close").click(); >About</a>
			</li>
			<li><a href="#services" onclick=$("#menu-close").click(); >Services</a>
			</li>
			<li><a href="#contact" onclick=$("#menu-close").click(); >Contact</a>
			</li>
		</ul>
	</nav>

	<!-- Header -->
	<header id="top" class="header">
		<div class="text-vertical-center">
			<h1>Foodie</h1>
			<h3>Restaurant Recommendation System</h3>
			<br> <a href="#about"><button type="button"
					class="btn btn-default btn-lg btn-round">
					<span class="glyphicon glyphicon-menu-down"></span>
				</button></a>
		</div>
	</header>

	<!-- About -->
	<section id="about" class="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Naive-Bayes Classifier powered by sentiment analysis of
						900,000 crowd sourced reviews.</h2>
					<p class="lead">Improved accuracy by use of intelligent feature
						selection algorithm. Open source RESTful API for public access.</p>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</section>

	<!-- Services -->
	<!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
	<section id="services" class="services bg-primary">
		<div class="container">
			<div class="row text-center">
				<div class="col-lg-10 col-lg-offset-1">
					<h2>Services</h2>
					<!-- <hr class="small"> -->
					<div class="row">
						<div class="col-md-3 col-sm-6">
							<div class="service-item">
								<span class="fa-stack fa-4x"> <i
									class="fa fa-circle fa-stack-2x"></i> <a
									href="business.jsp#loc_rest"><i id="flocation"
										class="fa fa-map-marker fa-stack-1x text-primary"></i></a>
								</span>
								<h4>
									<strong>Location</strong>
								</h4>
								<p>Find the list of all best restaurants in a city with
									their Foodie ratings.</p>
							</div>
						</div>
						<div class="col-md-3 col-sm-6">
							<div class="service-item">
								<span class="fa-stack fa-4x"> <i
									class="fa fa-circle fa-stack-2x"></i> <a
									href="business.jsp#business"><i id="fbusiness"
										class="fa fa-yelp fa-stack-1x text-primary"></i></a>
								</span>
								<h4>
									<strong>Business</strong>
								</h4>
								<p>Find details for a particular business using RESTful API.</p>
							</div>
						</div>
						<div class="col-md-3 col-sm-6">
							<div class="service-item">
								<span class="fsentiment fa-stack fa-4x"> <i
									class="fa fa-circle fa-stack-2x"></i> <a
									href="business.jsp#rev-rest"><i id="fsentiment"
										class="fa fa-cogs fa-stack-1x text-primary"></i> </a>
								</span>
								<h4>
									<strong>Sentiment Analysis</strong>
								</h4>
								<p>Try posting a new review and get instant review from our
									awesome NB Classifier.</p>
							</div>
						</div>
						<div class="col-md-3 col-sm-6">
							<div class="service-item">
								<span class="fa-stack fa-4x"> <i
									class="fa fa-circle fa-stack-2x"></i> <a href="#charts"><i
										id="fchart" class="fa fa-pie-chart fa-stack-1x text-primary"></i>
								</a>
								</span>
								<h4>
									<strong>Statistics</strong>
								</h4>
								<p>Dive deep into details of our classification engine. See
									how we evolved our core-algorithm.</p>
							</div>
						</div>
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.col-lg-10 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</section>
	<!-- Callout -->
	<aside class="callout">
		<div class="text-vertical-center">
			<h1>Chi-Square Word Importance Differentiation Selection</h1>
		</div>
	</aside>
	<a href="/foodie/FoodieReport.pdf">Download Report</a>
	<!-- <div id="chart_div" style="width: 900px; height: 500px;"></div> -->
	<p id="charts">
	<div class="grid">
		<div class="col-1-2">
			<div id="chart_div1" class="chart"></div>
		</div>
		<div class="col-1-2">
			<div id="chart_div2" class="chart"></div>
		</div>
	</div>
	<div class="grid">
		<div class="col-1-2">
			<div id="pie_chart1" class="chart"></div>
		</div>
		<div class="col-1-2">
			<div id="pie_chart2" class="chart"></div>
		</div>
	</div>
	</p>
	<!-- Portfolio -->
	<section id="portfolio" class="portfolio">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h2>Discover</h2>
					<hr class="small">
					<div class="row">
						<div class="col-md-6">
							<div class="portfolio-item">
								<a href="#"> <img class="img-portfolio img-responsive"
									src="img/portfolio-1.jpg">
								</a>
							</div>
						</div>
						<div class="col-md-6">
							<div class="portfolio-item" class="img-responsive">
								<a href="#"> <img class="img-portfolio img-responsive"
									src="img/portfolio-2.jpg">
								</a>
							</div>
						</div>
						<div class="col-md-6">
							<div class="portfolio-item">
								<a href="#"> <img class="img-portfolio img-responsive"
									src="img/portfolio-3.jpg">
								</a>
							</div>
						</div>
						<div class="col-md-6">
							<div class="portfolio-item">
								<a href="#"> <img class="img-portfolio img-responsive"
									src="img/portfolio-4.jpg">
								</a>
							</div>
						</div>
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.col-lg-10 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</section>

	<!-- Map -->
	<section id="contact" class="map">
		<iframe width="100%" height="100%" frameborder="0" scrolling="no"
			marginheight="0" marginwidth="0"
			src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11655.812085854262!2d-77.6763653!3d43.084483!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xbe7eb31ed5e22c03!2sRochester+Institute+of+Technology!5e0!3m2!1sen!2sus!4v1462382980896"></iframe>
		<br />
		</iframe>
	</section>

	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h4>
						<strong>Foodie Restaurants Recommendation</strong>
					</h4>
					<p>
						250, Bennington Hills<br>West Henrietta, NY 14586
					</p>
					<ul class="list-unstyled">
						<li><i class="fa fa-phone fa-fw"></i> (585) 400-7893</li>
						<li><i class="fa fa-envelope-o fa-fw"></i> <a
							href="mailto:am3926@rit.edu">am3926@rit.edu</a></li>
					</ul>
					<br>
					<ul class="list-inline">
						<li><a href="http://www.fb.com/anurag.malik.23"><i
								class="fa fa-facebook fa-fw fa-3x"></i></a></li>
						<li><a href="http://www.bitbucket.org/anurag23"><i
								class="fa fa-dribbble fa-fw fa-3x"></i></a></li>
					</ul>
					<hr class="small">
					<p class="text-muted">Copyright &copy; Anurag / Lokesh 2016</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/charts.js"></script>

	<!-- Custom Theme JavaScript -->
	<script>
	$(document).ready(function() {
		$("#flocation").mouseover( function(){
			$(this).removeClass('fa-map-marker');
			$(this).addClass('fa-sign-in');
		});
		$("#flocation").mouseout( function(){
			$(this).addClass('fa-map-marker');
			$(this).removeClass('fa-sign-in');
		});
		
		$("#fbusiness").mouseover( function(){
			$(this).removeClass('fa-yelp');
			$(this).addClass('fa-sign-in');
		});
		$("#fbusiness").mouseout( function(){
			$(this).addClass('fa-yelp');
			$(this).removeClass('fa-sign-in');
		}); 
		
		$("#fsentiment").mouseover( function(){
			$(this).removeClass('fa-cogs');
			$(this).addClass('fa-sign-in');
		});
		$("#fsentiment").mouseout( function(){
			$(this).addClass('fa-cogs');
			$(this).removeClass('fa-sign-in');
		}); 
		
		$("#fchart").mouseover( function(){
			$(this).removeClass('fa-pie-chart');
			$(this).addClass('fa-sign-in');
		});
		$("#fchart").mouseout( function(){
			$(this).addClass('fa-pie-chart');
			$(this).removeClass('fa-sign-in');
		}); 
	});
</script>

	<script>
    // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Scrolls to the selected menu item on the page
    $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
            if (location.pathname.replace('/^', '') == this.pathname.replace('/^', '') || location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
              }
            });
        });
</script>

</body>

</html>
