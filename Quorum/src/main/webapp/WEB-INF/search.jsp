<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="head.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl5+z0I5TTZsxPhl5Q9hFL2K9I6IBh5Dw6d5V6e5qK"
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9I2qC/0fNfBfVWUtvzByEXhLQzCLpAd/4tzf1zzen4M1Qv05orXvneYJ"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

</head>

<body>
	<jsp:include page="navbar.jsp" />
	<main>
		<div class="sm-spacer"></div>
		<div id="accordion">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-11 mb-3">
					<h3>Search Results For: "${query}"</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-1">
					<div class="card">
						<button class="btn card-header" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">Users</button>
					</div>
					<div class="search-selector-spacer"></div>
					<div class="card">
						<button class="btn collapsed card-header" data-toggle="collapse"
							data-target="#collapseTwo" aria-expanded="false"
							aria-controls="collapseTwo">Schools</button>
					</div>
					<div class="search-selector-spacer"></div>
					<div class="card">
						<button class="btn collapsed card-header" data-toggle="collapse"
							data-target="#collapseThree" aria-expanded="false"
							aria-controls="collapseThree">Groups</button>
					</div>
					<div class="search-selector-spacer"></div>
					<div class="card">
						<button class="btn collapsed card-header" data-toggle="collapse"
							data-target="#collapseFour" aria-expanded="false"
							aria-controls="collapseFour">Posts</button>
					</div>
				</div>
				<div class="col-9">
					<div id="collapseOne" class="collapse show"
						data-parent="#accordion">
						<div class="card-body scrollable-card" data-bs-spy="scroll"
							data-bs-smooth-scroll="true" data-bs-target="#collapseOne"
							tabindex="0">
							<c:forEach var="resultUser" items="${userResults}">
								<a class="wrapper-link"
									href="userProfile.do?userID=${resultUser.id}">
									<div class="card-body result-card">
										<div class="row">
											<div class="col-2">${resultUser.firstName}
												${resultUser.lastName}</div>
											<div class="col-2"></div>
											<div class="col-4">${resultUser.username}</div>
											<div class="col-4">${resultUser.school.name}</div>
										</div>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body scrollable-card" data-bs-spy="scroll"
							data-bs-smooth-scroll="true" data-bs-target="#collapseOne"
							tabindex="0">
							<c:forEach var="resultSchool" items="${schoolResults}">
								<a class="wrapper-link"
									href="school.do?schoolID=${resultSchool.id}">
									<div class="card-body result-card">
										<div class="row">
											<div class="col-4">${resultSchool.name}</div>
											<div class="col-8">
												<p class="card-text result-card-description">${resultSchool.description}
												</p>
											</div>
										</div>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>

					<div id="collapseThree" class="collapse" data-parent="#accordion">
						<div class="card-body">Content of Div 3</div>
					</div>
					<div id="collapseFour" class="collapse" data-parent="#accordion">
						<div class="card-body scrollable-card" data-bs-spy="scroll"
							data-bs-smooth-scroll="true" data-bs-target="#collapseOne"
							tabindex="0">
							<c:forEach var="resultPost" items="${postResults}">
								<a class="wrapper-link" href="post.do?postID=${resultPost.id}">
									<div class="card-body result-card">
										<div class="row">
											<div class="col-6">
												<h6>@${resultPost.user.username}</h6>
											</div>
											<div class="col-6">
												<h6>${resultPost.socialGroup.name}</h6>
											</div>

										</div>
										<div class="row">

											<h5 class="card-title">${resultPost.title}</h5>
										</div>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
		<div class="sm-spacer"></div>
	</main>
	<jsp:include page="footer.jsp" />
</body>

</html>