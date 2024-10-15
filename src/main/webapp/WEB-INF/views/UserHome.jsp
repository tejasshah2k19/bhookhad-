<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Food Blog Search</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS for Styling -->
<style>
body {
	background-color: #f8f9fa;
}

.search-container {
	margin-top: 100px;
}

.search-title {
	text-align: center;
	margin-bottom: 30px;
}

.food-blog-title {
	font-size: 3rem;
	color: #dc3545;
}

.search-results {
	margin-top: 40px;
}

.recipe-card {
	margin-bottom: 20px;
}
</style>
</head>
<body>

	<!-- Main Container -->
	<div class="container search-container">
		<h1 class="food-blog-title text-center">Hungry kyaa!!!</h1>
		<p class="text-center text-muted">Search for offers and more...</p>

		<!-- Search Form -->
		<div class="row justify-content-center">
			<div class="col-md-8">
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search"
						placeholder="Search offers..." aria-label="Search">
					<button class="btn btn-danger" type="submit">Search</button>
				</form>
			</div>
		</div>

		<!-- Search Results Section -->
		<div class="search-results">
			<h3 class="search-title text-muted text-center">Search Results</h3>

			<div class="row">
				<!-- Example Recipe Card 1 -->

				<c:forEach items="${offers}" var="o">
					<div class="col-md-4">
						<div class="card recipe-card">
							<img src="${o.offerPicPath}"
								class="card-img-top" alt="Delicious Pasta">
							<div class="card-body">
								<h5 class="card-title">${o.title}</h5>
								<p class="card-text">${o.description}</p>
								<a href="#" class="btn btn-danger">Read More</a>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>
	</div>

	<!-- Bootstrap JS and Dependencies -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
