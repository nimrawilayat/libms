	// Handle Create Book
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
	
	// Handle Update Book
	$("table span.glyphicon-pencil").on("click", function() {
		var bookId = $(this).closest("tr").attr("data-book-id")
		var editFormUrl = "/libms/book/" + bookId + "/edit"
		location.href = editFormUrl;
	});
	
	// Handle Delete Book
	$("table span.glyphicon-trash").on("click", function() {
		if (confirm("Are you sure you want to delete this Book?")) {
			var bookId = $(this).closest("tr").attr("data-book-id")
			var url = "/libms/book/" + bookId + "/delete"
			location.href = url;	
		}	
	});
	
	// Fetch Books from server using ajax
	var fetchBooks = function() {
		$.ajax({
			dataType : 'json',
		  url : '/libms/v1/books',
		  type : "get"
		}).done(function(data, textStatus, jqXHR) {  
		  $.each(data, function(i, book) {
		    console.log(book.id + ', ' + book.title + ', ' + book.author);        
        $('<tr data-book-id=' + book.id + '>').append(
          $('<td>').text(book.id),
          $('<td>').text(book.title),
          $('<td>').text(book.author),
          $('<td>').html('<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'),
          $('<td>').html('<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>')
        ).appendTo('#book-table-json');
		  });
		}).fail(function(jqXHR, textStatus, errorThrown) {
		  console.log('Failed to fetch books using ajax');
		}).always(function(dataOrjqXHR, textStatus, jqXHRorErrorThrown) {
			console.log("I'm always called, regardless of success or error");
		});
	};
	
	fetchBooks();
	
	
	var $bookModal = $("#book-modal").modal({
	  backdrop: 'static',
	  show: false
	});

	// Trigger Headline modal
	$("#book-table-json").on("click", "span.glyphicon-pencil", function() {
		var bookId = $(this).closest("tr").attr("data-book-id");
		
		$.ajax({
			dataType : 'json',
		  url : '/libms/v1/book/' + bookId,
		  type : "get"
		}).done(function(data, textStatus, jqXHR) {  
		  console.log(data);
		}).fail(function(jqXHR, textStatus, errorThrown) {
		  console.log('Failed to fetch book using ajax');
		}).always(function(dataOrjqXHR, textStatus, jqXHRorErrorThrown) {
			console.log("I'm always called, regardless of success or error");
		});
		
		
	  $bookModal.modal('show');
	});

	// Cancel changes
	$("#cancel-edit-photo").on("click", function() {
	  $bookModal.modal('hide');
	});
