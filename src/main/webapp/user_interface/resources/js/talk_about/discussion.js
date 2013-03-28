function submitForm () {
	
	var textMessage = $("#textareaPostMessage").val();
	
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
	
}

function formatMinTwoDigits (number){
	return number > 9 ? "" + number : "0" + number;
}

function formatDate (date){
	
	var day = formatMinTwoDigits(date.getDate());
    var month = formatMinTwoDigits(date.getMonth() + 1);
    var year = formatMinTwoDigits(date.getFullYear());
    var hour = formatMinTwoDigits(date.getHours());
    var minute = formatMinTwoDigits(date.getMinutes());
	
	return day + '/' + month + '/' + year + ' ' + hour + ':' + minute;
}

function getNewDiscussionMessages (subject) {
	
	var url = "list/" + $("#subject").val();
	
	$.get(url, function(data, status){
		var messages = data;
		
		for (var i = 0, len = messages.length; i < len; i++){
			var message =  messages[i];
			
			var pMessage = document.createElement('p');
			pMessage.className = 'discussion-text-message';
			pMessage.innerHTML = message.text;
				
			var date = new Date(message.date);
			
			var pMessageAbout = document.createElement('p');
			pMessageAbout.className = 'discussion-about-text-message';
			pMessageAbout.innerHTML = message.author + " - " + formatDate(date);
			
			var div = document.createElement('div');
			div.id = message.id;
			div.appendChild(pMessage);
			div.appendChild(pMessageAbout);
			
			$("#discussionPostArea").append(div);
		}
	  }, "json");
}

function checkHasNewDiscussionMessages () {
	
	setInterval(function(){alert("Hello")},3000);	
	$("#").append();
}