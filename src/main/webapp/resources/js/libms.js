$("#submit-button").on("click", function() {
//	var id = $("#id").val();
//	var title = $("#title").val();
//	var author = $("#author").val();
//	
//	var reId = new RegExp("^\d{1,20}$");
//	var validId = reId.test(id);
//	
//	var reText = new RegExp("^[A-Za-z0-9]{2,100}$");
//	var validTitle = reText.test(title);
//	var validAuthor = reText.test(author);
//	
//	if (!validId || !validTitle || !validAuthor) {
//		alert("Input is not valid");
//		return;
//	} else {
		$("#the-form").submit();
//	}
	
});

	var re = new RegExp($("#regex").val());
	var pattern = $("#pattern").val();
	
	var result = re.test(pattern);
	var message = "Pattern does not Match";
	
	if (result) {
		message = "Pattern Matches";
	} 
	
	$("#regex-pattern-result").text(message);