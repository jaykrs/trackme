package com.teqto.trackme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teqto.trackme.model.Page;

/**
 * @author jayant
 *
 */
public interface PageRepository extends JpaRepository<Page, Integer>{
	
}
