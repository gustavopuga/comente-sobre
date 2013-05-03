<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value="/user_interface/resources/css/index.css"/>">
<script type="text/javascript" src="<c:url value="/user_interface/resources/js/talk_about/index.js"/>"></script>

<div class='content'>

	<h2>Busque um assunto e comece a comentar sobre ele agora:</h2>
	<div class='index-input-div'>
		<form id="form" onsubmit="submitForm()" method="POST">
			<input type="text" id="subject" name="subject" class="input-text"/>
			<input type="submit" value="Buscar" id='button' class="input-button"/>
		</form>
	</div>

</div>