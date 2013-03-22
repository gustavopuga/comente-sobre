<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="<c:url value="/user_interface/resources/css/discussion.css"/>">
<script type="text/javascript" src="<c:url value="/user_interface/resources/js/talk_about/discussion.js"/>"></script>

<div class='content'>
	
	<div>
		<h2>Assunto: ${discussion.subject}</h2>
	</div>
	
	<div class='discussion-post-area'>
		<c:forEach items="${discussion.messages}" var="message">
		<div>
			<p class='discussion-text-message'>
				${message.text} <br>
				<p class='discussion-about-text-message'>${message.author} - <fmt:formatDate value="${message.date}" pattern="dd/MM/yyyy HH:mm" /></p>
			</p>
		</div>
		</c:forEach>
	</div>
		
	<form id="form" action="update" onsubmit="submitForm()" method="POST">
		<input type="hidden" id="subject" name="subject" value=${discussion.subject}>
		<input type="hidden" id="text" name="text" value="">
		
		<div class='discussion-post-message'>
			<h4>Deixe seu coment&aacute;rio aqui...</h4>
			<img src="<c:url value="/user_interface/resources/images/avatar_user_64.png"/>">
			
			<textarea id='textareaPostMessage'></textarea>
			
			<div class="discussion-post-message-input-area">
				Autor: <input type='text' id='author' name='author'/>
			</div>
			
			<div class="discussion-post-message-input-area">
				<input id="postMessageButton" type="submit" class="input-button" value="Comentar"/>
			</div>
		</div>
		
	</form>
	
</div>