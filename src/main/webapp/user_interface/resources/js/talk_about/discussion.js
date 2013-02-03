function submitForm () {
	
	var textMessage = $("#textarea-post-message").val();
	
	if (textMessage == null || textMessage.replace(/\s/g,"") == ""){
		alert("Para comentar é necessário escrever um comentário primeiro.");
		return false;
	}else{
		$("#text").val(textMessage);
	}
	
	var author = $("#author").val();
	if (author == null || author.replace(/\s/g,"") == ""){
		$("#author").val("Desconhecido");
	}
	
	$("#form").submit();
	
	return true;
}