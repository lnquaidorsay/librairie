package com.librairie.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.librairie.domain.security.Book;
import com.librairie.domain.security.Response;
import com.librairie.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Book addBookPost(@RequestBody Book book) {
		return bookService.save(book);
	}

	@RequestMapping(value = "/add/image", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity upload(@RequestParam("id") Long id, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Book book = bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			// String fileName = id + ".png";
			String fileName = id + ".jpg";

			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
			stream.write(bytes);
			stream.close();

			Response respUploadAdd = new Response();
			respUploadAdd.setName("Upload Success!");
			return new ResponseEntity<>(response, HttpStatus.OK);

			// return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Response respUploadAdd = new Response();
			respUploadAdd.setName("Upload failed!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			// return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update/image", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateImagePost(@RequestParam("id") Long id, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Book book = bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id + ".jpg";

			// Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));

			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
			stream.write(bytes);
			stream.close();

			Response respUploadUpdate = new Response();
			respUploadUpdate.setName("Upload Success!");
			return new ResponseEntity<>(response, HttpStatus.OK);

			// return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Response respUploadUpdate = new Response();
			respUploadUpdate.setName("Upload failed!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			// return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/bookList")
	public List<Book> getBookList() {
		return bookService.findAll();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Book updateBookPost(@RequestBody Book book) {
		return bookService.save(book);
	}

//	@RequestMapping(value = "/remove", method = RequestMethod.POST)
//	public ResponseEntity remove(@RequestBody String id) {
//		bookService.removeOne(Long.parseLong(id));
//
//		return new ResponseEntity("Remove Success!", HttpStatus.OK);
//	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity remove(@RequestBody String id) {
		bookService.removeOne(Long.parseLong(id));
		Response response = new Response();
		response.setName("Remove Success!");
		return new ResponseEntity<>(response, HttpStatus.CREATED);

		// return new ResponseEntity("Remove Success!", HttpStatus.OK);
	}

	@RequestMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id) {
		Book book = bookService.findOne(id);
		return book;
	}
}
