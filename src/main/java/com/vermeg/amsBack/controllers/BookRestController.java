package com.vermeg.amsBack.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vermeg.amsBack.entities.Book;
import com.vermeg.amsBack.entities.Provider;
import com.vermeg.amsBack.exception.ResourceNotFoundException;
import com.vermeg.amsBack.repositories.BookRepository;


@RestController
@RequestMapping({ "/book" })
@CrossOrigin(origins="*")
public class BookRestController {
	
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
	@Autowired
	private BookRepository bookRepository;
	

	@GetMapping("/list")
	public List<Book> getAllBook() {
		return (List<Book>) bookRepository.findAll();
	}
	

	@PostMapping("/add")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

	@PutMapping("/{bookId}")
	public Book updateBook(@PathVariable Long bookId, @Valid @RequestBody Book bookRequest) {
		return bookRepository.findById(bookId).map(book -> {
			book.setTitre(bookRequest.getTitre());
			book.setAuthor(bookRequest.getAuthor());
			book.setPrix(bookRequest.getPrix());
			book.setDatecreation(bookRequest.getDatecreation());
			//book.setPicture(bookRequest.getPicture());
			book.setNbrstock(bookRequest.getNbrstock());
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("Book Id " + bookId + " not found"));
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
		return bookRepository.findById(bookId).map(book -> {
			bookRepository.delete(book);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Book Id " + bookId + " not found"));
	}
	
	@GetMapping("/{bookId}")
	public Optional<Book> getBook(@PathVariable Long bookId) {
		return bookRepository.findById(bookId);
	}
}