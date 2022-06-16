<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Product Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Product Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Products</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<caption>
					<h2>
						<c:if test="${product != null}">
            			Edit Product
            		</c:if>
						<c:if test="${product == null}">
            			Add New product
            		</c:if>
					</h2>
				</caption>
				
				<c:if test="${product != null}">
					<form action="update" method="post">
					
					<input type="hidden" name="id" value="<c:out value='${product.id}' />" />
					
					<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Product Description</label> <input type="text"
						value="<c:out value='${product.description}' />" class="form-control"
						name="description">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Product Category</label> <input type="text"
						value="<c:out value='${product.category}' />" class="form-control"
						name="category">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Product Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
					</fieldset>
					
					<button type="submit" class="btn btn-success">Save</button>
					
					</form>
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post">
					
					<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Product Description</label> <input type="text"
						value="<c:out value='${product.description}' />" class="form-control"
						name="description">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Product Category</label> <input type="text"
						value="<c:out value='${product.category}' />" class="form-control"
						name="category">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Product Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
					</fieldset>
					
					<button type="submit" class="btn btn-success">Save</button>
					
					</form>
				</c:if>

			</div>
		</div>
	</div>
</body>
</html>