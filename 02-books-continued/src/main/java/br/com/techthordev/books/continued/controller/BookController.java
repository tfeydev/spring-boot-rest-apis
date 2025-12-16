package br.com.techthordev.books.continued.controller;

import br.com.techthordev.books.continued.entity.Book;
import br.com.techthordev.books.continued.exception.BookNotFoundException;
import br.com.techthordev.books.continued.request.BookRequest;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Books Rest API Entdpoints", description = "Operations related to books")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(
            List.of(
                new Book(
                    1,
                    "Compouter Science Pro",
                    "Chad Darby",
                    "Computer Science",
                    5
                ),
                new Book(2, "Java Spring Master", "Eric Roby", "Math", 5),
                new Book(
                    3,
                    "Why 1 + 1 Rocks",
                    "Adil A.",
                    "Computer Science",
                    5
                ),
                new Book(4, "How Bears Hibernate", "Bob B.", "Science", 2),
                new Book(5, "A Pirate's Treasure", "Curt C.", "History", 3),
                new Book(6, "Why 2 + 2 is Better", "Dan D.", "Math", 1)
            )
        );
    }

    @Operation(summary = "Get all books", description = "Retrieve a list of all available books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(
        @Parameter(description = "Optional query parameter")
        @RequestParam(required = false) String category
    ) {
        if (category == null) {
            return books;
        }

        return books
            .stream()
            .filter(book -> book.getCategory().equalsIgnoreCase(category))
            .toList();
    }

    @Operation(summary = "Get book by ID", description = "Retrieve a book by its ID")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(
            @Parameter(description = "Id of book to be retrieved")
            @PathVariable @Min(value = 1) Long id) {
        return books
            .stream()
            .filter(book -> book.getId() == id)
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException("Book not found - " + id)
            );
    }

    @Operation(summary = "Create a new book", description = "Add a new book to the list")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        //        long id;
        //
        //        if (books.isEmpty()) {
        //            id = 1;
        //        } else {
        //            id = books.get(books.size() - 1).getId() + 1;
        //        }
        long id = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() + 1;

        Book book = convertToBook(id, bookRequest);

        books.add(book);
    }

    @Operation(summary = "Update an existing book", description = "Update the details of an existing book by its ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Book updateBook(
        @Parameter(description = "Id of book to be updated")
        @PathVariable @Min(value = 1) Long id,
        @Valid @RequestBody BookRequest bookRequest
    ) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        throw new BookNotFoundException("Book not found - " + id);
    }

    @Operation(summary = "Delete a book", description = "Remove a book from the list by its ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(
            @Parameter(description = "Id of book to be deleted")
            @Valid @PathVariable @Min(value = 1) Long id) {
        books
            .stream()
            .filter(book -> book.getId() == id)
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException("Book not found - " + id)
            );
        books.removeIf(book -> book.getId() == id);
    }

    private Book convertToBook(long id, BookRequest bookRequest) {
        return new Book(
            id,
            bookRequest.getTitle(),
            bookRequest.getAuthor(),
            bookRequest.getCategory(),
            bookRequest.getRating()
        );
    }
}
