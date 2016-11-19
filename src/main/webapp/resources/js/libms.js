$("#submit-button").on("click", function() {
	
	// Clear any previously found error
	$("#the-form")
		.find(".form-group")
		.removeClass("has-error");
	
	var title = $("#title").val();
	var author = $("#author").val();
	
	var regex = new RegExp("^[a-zA-Z0-9 &_+-]{5,45}$");
	
	if (!regex.test(title)) {
		$("#title")
			.closest("div.form-group")
			.addClass("has-error");
		
		return;
	}
	
	if (!regex.test(author)) {
		$("#author")
			.closest("div.form-group")
			.addClass("has-error");
		
		return;
	}
	
	$("#the-form").submit();
	
});

// Handle Book update
$("table.book-table span.glyphicon-pencil").on("click", function() {
	var bookId = $(this).closest("tr").attr("data-book-id")
	var editFormUrl = "/libms/book/" + bookId + "/edit"
	location.href = editFormUrl;
});

$("table.book-table span.glyphicon-trash").on("click", function() {
	if (confirm("Are you sure you want to delete this Book?")) {
		var bookId = $(this).closest("tr").attr("data-book-id")
		var url = "/libms/book/" + bookId + "/delete"
		location.href = url;	
	}	
});

	