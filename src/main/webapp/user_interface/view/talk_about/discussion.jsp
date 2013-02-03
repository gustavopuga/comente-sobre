<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript"
	src="<c:url value="/user_interface/resources/js/talk_about/discussion.js"/>"></script>

<table>
	
	<div>
		<h2>Assunto: ${discussion.subject}</h2>
	</div>
	
	<div class='post-area'>
		<c:forEach items="${discussion.messages}" var="message">
		<div>
			<p class='text-message'>
				${message.text} <br>
				<p class='text-identify'>${message.author} - <fmt:formatDate value="${message.date}" pattern="dd/MM/yyyy HH:mm" /></p>
			</p>
		</div>
		</c:forEach>
	</div>
		
	<form id="form" action="update" onsubmit="submitForm()" method="POST">
		<input type="hidden" id="subject" name="subject" value=${discussion.subject}>
		<input type="hidden" id="text" name="text" value="">
		
		<div class='post-message'>
			<h4>Deixe seu coment&aacute;rio aqui...</h4>
			<img src="<c:url value="/user_interface/resources/images/avatar_user_64.png"/>">
			
			<textarea id='textarea-post-message'></textarea>
			
			<div class="post-message-input-area">
				Autor: <input type='text' id='author' name='author'/>
			</div>
			
			<div class="post-message-input-area">
				<input id="post-message-button" type="submit" class="inputButton" value="Comentar"/>
			</div>
		</div>
		
	</form>
	
</table>