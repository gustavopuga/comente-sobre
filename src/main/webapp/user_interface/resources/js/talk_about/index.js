var globalUrl = 'http://suggestqueries.google.com/complete/search?hl=pt-BR';

function createArrayOfSuggestions(m) {

	var suggestions = new Array();		

	for ( var i = 0; i < m.length; i++ ) {
		var suggestion = eval(m[i]);
		suggestions[i] = suggestion[0];
	}

	return suggestions;

}

function prettyUrl() {
    var url = $("#subject").val().toLowerCase();
    
    var a_chars = new Array(
    	    new Array("a",/[áàâãªÁÀÂÃ]/g),
    	    new Array("e",/[éèêÉÈÊ]/g),
    	    new Array("i",/[íìîÍÌÎ]/g),
    	    new Array("o",/[òóôõºÓÒÔÕ]/g),
    	    new Array("u",/[úùûÚÙÛ]/g),
    	    new Array("c",/[çÇ]/g),
    	    new Array("n",/[Ññ]/g)
    	  );
    
    for(var i=0;i<a_chars.length;i++)
    	url = url.replace(a_chars[i][1],a_chars[i][0]);
    
    url = url.replace(/^\s+|\s+$/g, "");
    url = url.replace(/\s+/g, "-");
    
    return url;
}

function submitForm() {
	
	var submitUrl = prettyUrl();
	
	$("#form").attr("action", submitUrl);
	$("#form").submit();
	
	return true;
}

function retrieve(url) {

	$.ajax({
		url: url,
		async: 'false',
		dataType: 'jsonp',
		data: {
		q: $("#txtAutoSuggest").val(),
		nolabels: 't',
		client: 'psy',
		ds: ''
		},
		success: function(data) {
			var myMap = eval(data[1]);	
			var suggestions = createArrayOfSuggestions(myMap);

			console.log(suggestions);
			autoSuggest(suggestions);

		}
	}); 

}	

function autoCompleteText() {
	var url = 'http://suggestqueries.google.com/complete/search?hl=pt-BR';
	retrieve(url);
}

function autoSuggest(data) {
	$("#txtAutosuggest").autocomplete(data);
}

$(function() {

    $("#subject").autocomplete({
        source: function( request, response ) {
            $.ajax({
                url: globalUrl,
                dataType: "JSONP",
                data: {
					q: $("#subject").val(),
					client: 'psy',
					ds: '',
                    featureClass: "P",
                    style: "full",
                    maxRows: 12,
                    name_startsWith: request.term
                },
                success: function( data ) {

                    response( $.map( createArrayOfSuggestions(data[1]), function( item ) {
                        return {
                            label: $("<span>").html(item).text(),
                            value: $("<span>").html(item).text()
                        }
                    }));

                }
            });
        },
        minLength: 2,
    });
});
