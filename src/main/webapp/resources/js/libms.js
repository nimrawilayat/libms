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
		}).done(function(books, textStatus, jqXHR) {
	    // First clear out the rows which may have been previously created
	    $('#book-table-json tr[data-book-id]').remove();

		  $.each(books, function(i, book) {
		    console.log(book.id + ', ' + book.title + ', ' + book.author);   
		    
		    /*$('<div/>').text(book.id + ', ' + book.title + ', ' + book.author).appendTo('#book-container');*/

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

	$("#show-create-book-modal").on("click", function() {
		$("#book-form").trigger('reset');
		$bookModal.modal('show');
	});
	
	$("#save-book").on("click", function() {
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
		
		
		// Determine if we are creating a new book or updating an existing book.
		// In the New book case, id will be null.
		// However, id will not be null if we were updating an existing book
		var id = $("#id").val();
		
		var url = '/libms/v1/books';
		var httpMethod = 'post';
		
		if (id) {
			url = url + '/' + id;
			httpMethod = 'put';
		}
		
		$.ajax({
		  url : url,
		  type : httpMethod,
		  data: $("#book-form").serialize(),
		  headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
		  }
		}).done(function(data, textStatus, jqXHR) {
			if (data === 'OK') {
				fetchBooks();
			} else {
				alert('Failed');
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
		  console.log('Failed to create book using ajax');
		}).always(function(dataOrjqXHR, textStatus, jqXHRorErrorThrown) {
			$bookModal.modal('hide');
		  $("#book-form").trigger('reset');
		});
	});
		
	$("#book-table-json").on("click", "span.glyphicon-pencil", function() {
		var bookId = $(this).closest("tr").attr("data-book-id");
		
		$.ajax({
			dataType : 'json',
		  url : '/libms/v1/book/' + bookId,
		  type : "get"
		}).done(function(data, textStatus, jqXHR) {  
		  console.log(data);
		  $("#id").val(data.id);
		  $("#title").val(data.title);
		  $("#author").val(data.author);
		}).fail(function(jqXHR, textStatus, errorThrown) {
		  console.log('Failed to fetch book using ajax');
		}).always(function(dataOrjqXHR, textStatus, jqXHRorErrorThrown) {
			console.log("I'm always called, regardless of success or error");
		});
				
	  $bookModal.modal('show');
	});

	// Cancel changes
	$("#cancel-book").on("click", function() {		
	  $bookModal.modal('hide');
	  $("#book-form").trigger('reset');
	});
