<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Insert title here</title>
</head>
<body>
	<div style="position: relative">
		<div class="container">
			<div class="card card-white">
				<div class="card-body">
					<input type="text" class="form-control add-task"
						placeholder="New Task...">
					<ul class="nav nav-pills todo-nav">
						<li role="presentation" class="nav-item all-task active"><a
							href="#" class="nav-link">All</a></li>
						<li role="presentation" class="nav-item active-task"><a
							href="#" class="nav-link">Active</a></li>
						<li role="presentation" class="nav-item completed-task"><a
							href="#" class="nav-link">Completed</a></li>
					</ul>
					<div class="todo-list">
						<c:forEach items="${list}" var="dto">
							<div class="todo-item jumbotron ${dto.finished?'complete':'' }"
								data-num="${dto.tno }">
								<div class="checker">
									<span class=""> <input type="checkbox"
										${dto.finished?"checked":"" }></span>
								</div>
								<span><c:out value="${dto.content }" /></span> <a
									class="float-right remove-todo-item"><i
									class="fa-solid fa-xmark"></i></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

			<form id="todoForm" method="post">
				
			</form>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/resources/script.js"></script>
	<script>
		$(document).ready(function() {
			
			//등록
			 $(".add-task").keypress(function (e) {
				 
				 if ((e.which == 13)&&(!$(this).val().length == 0)) {

					const text = $(this).val();
					
					const textInput = $("<input type='hidden' name='content' value='" + text +"'/>");
					
					$("#todoForm").append(textInput);
					
					$("form").attr("action", "/todo/register").submit();
					
		        } else if(e.which == 13) {
		            alert('Please enter new task');
		        }
			})
			
			//완료or완료취소
			$("input[type=checkbox]").change(function() {
				
				const tno = $(this).closest(".todo-item").data("num");
				const complete = !$(this).closest(".todo-item").hasClass("complete");
				
				const tnoInput = $("<input type='hidden' name='tno' value='" + tno +"'/>");
				const finishedInput = $("<input type='hidden' name='finished' value='" + complete +"'/>");
				
				$("#todoForm").append(tnoInput).append(finishedInput);
				
				$("form").attr("action", "/todo/check").submit();
			})
			
			//삭제
			$(".remove-todo-item").click(function() {
				
				const tno = $(this).closest(".todo-item").data("num");
				const tnoInput = $("<input type='hidden' name='tno' value='" + tno +"'/>");
				$("#todoForm").append(tnoInput);

				$("#todoForm").attr("action", "/todo/remove").submit();
			})
			
		})
	</script>
</body>
</html>