package com.teqto.trackme.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teqto.trackme.model.Page;
import com.teqto.trackme.repository.PageRepository;

@RestController
public class PageController {

	private final Logger log = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private PageRepository pageRepository;
	
	@RequestMapping(value = "api/page/public/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> greeting(@PathVariable Integer id) throws URISyntaxException, NoSuchElementException {
		Optional<Page> page = pageRepository.findById(id);
		log.info("found page with id" + id);
		return page.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "api/page/create", method = RequestMethod.POST)
	ResponseEntity<?> createPage(@Valid @RequestBody Page page, HttpServletRequest request) throws URISyntaxException {
		Page result = null;
		log.info("Request to create Page: {}", page);
		result = pageRepository.saveAndFlush(page);
		return ResponseEntity.created(new URI("/api/page/create/" + result.getId())).body(result);
	}

	@RequestMapping(value = "api/page/update", method = RequestMethod.PUT)
	ResponseEntity<?> updatePage(@Valid @RequestBody Page page, HttpServletRequest request) throws URISyntaxException {
		Page result = null;
		log.info("Request to create Page: {}", page);
		result = pageRepository.save(page);
		return ResponseEntity.created(new URI("/api/page/update/" + result.getId())).body(result);
	}
}