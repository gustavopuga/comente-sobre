<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="<c:url value="/user_interface/resources/js/talk_about/index.js"/>"></script>

<div class='container'>

	<h2>Busque um assunto e comece a comentar sobre ele agora:</h2>
	 
	<div class='center_div'>
		<form id="form" onsubmit="submitForm()" method="POST">
			<input type="text" id="subject" class="inputText"/>
			<input type="submit" value="Buscar" id='button' class="inputButton"/>
		</form>
	</div>

</div>