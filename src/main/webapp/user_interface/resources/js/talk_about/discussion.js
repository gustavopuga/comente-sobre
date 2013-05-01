function submitForm() {
	
	var textMessage = $("#textareaPostMessage").val();
	var subject = $("#subject").val();
	var author = $("#author").val();
	alert("" +subject);
	if (textMessage == null || textMessage.replace(/\s/g,"") == ""){
		alert("Para comentar é necessário escrever um comentário primeiro.");
		return false;
	}
	
	if (author == null || author.replace(/\s/g,"") == ""){
		author = "Desconhecido";
	}
	
	var data = { author: author,
			     subject: subject, 
			     text: textMessage };
	
	var url = subject;
	
	$.ajax({
		type: "PUT",
		url: url,
		data: data,
		success: function() {
			$("#textareaPostMessage").val("");
			$("#author").val(author);
		}
	});
}

function formatMinTwoDigits (number){
	return number > 9 ? "" + number : "0" + number;
}

function formatDate (date) {
	
	var day = formatMinTwoDigits(date.getDate());
    var month = formatMinTwoDigits(date.getMonth() + 1);
    var year = formatMinTwoDigits(date.getFullYear());
    var hour = formatMinTwoDigits(date.getHours());
    var minute = formatMinTwoDigits(date.getMinutes());
    var second = formatMinTwoDigits(date.getSeconds());
	
	return day + '/' + month + '/' + year + ' - ' + hour + ':' + minute + ":" + second;
}

function getNewDiscussionMessages () {
	
	var date = $("#date").val();
	var url = $("#subject").val() + "/" + date;
	
	$.ajax({
		type: "GET",
		url: url,
		dataType: "JSON",
		success: function(data) {
			
			var messages = data;
			var lastDate;
			
			for (var i = 0, len = messages.length; i < len; i++){
				
				var message =  messages[i];
				
				var date = new Date(message.date);
				lastDate = message.date;
				
				var pMessageAbout = document.createElement('p');
				pMessageAbout.className = 'discussion-about-text-message';
				pMessageAbout.innerHTML = message.author + " - " + formatDate(date);
				
				var pMessage = document.createElement('p');
				pMessage.className = 'discussion-text-message';
				pMessage.innerHTML = message.text + "<br>";
				
				var div = document.createElement('div');
				div.id = message.id;
				div.appendChild(pMessage);
				div.appendChild(pMessageAbout);
				
				$("#discussionPostArea").append(div);
			}
			
			if (lastDate != null){
				$("#date").val(lastDate);
			}
		}
	});
	
}

function checkHasNewDiscussionMessages() {
	setInterval(function(){getNewDiscussionMessages()},1000);
}

$(checkHasNewDiscussionMessages());