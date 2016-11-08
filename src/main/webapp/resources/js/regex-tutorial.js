$("#regex-test").on("click", function() {
	var re = new RegExp($("#regex").val());
	var pattern = $("#pattern").val();
	
	var result = re.test(pattern);
	var message = "Pattern does not Match";
	
	if (result) {
		message = "Pattern Matches";
	} 
	
	$("#regex-pattern-result").text(message);
});