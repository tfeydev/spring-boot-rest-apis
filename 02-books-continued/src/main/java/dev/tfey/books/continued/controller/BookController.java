package dev.tfey.books.continued.controller;

import dev.tfey.books.continued.entity.Book;
import dev.tfey.books.continued.request.BookRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
            new Book(1, "Compouter Science Pro", "Chad Darby", "Computer Science", 5),
            new Book(2, "Java Spring Master", "Eric Roby", "Math", 5),
            new Book(3, "Why 1 + 1 Rocks", "Adil A.", "Computer Science", 5),
            new Book(4, "How Bears Hibernate", "Bob B.", "Science", 2),
            new Book(5, "A Pirate's Treasure", "Curt C.", "History", 3),
            new Book(6, "Why 2 + 2 is Better", "Dan D.", "Math", 1)
        ));
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {

        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{id}")
    public Book getBookByTitle(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void createBook(@RequestBody BookRequest bookRequest) {
//        long id;
//
//        if (books.isEmpty()) {
//            id = 1;
//        } else {
//            id = books.get(books.size() - 1).getId() + 1;
//        }
        long id = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() +1;

        Book book = convertToBook(id, bookRequest);

        books.add(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
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
