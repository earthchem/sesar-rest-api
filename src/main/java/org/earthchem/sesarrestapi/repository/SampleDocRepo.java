package org.earthchem.sesarrestapi.repository;

import org.earthchem.sesarrestapi.model.SampleDoc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDocRepo extends CrudRepository<SampleDoc, Integer> {
	}
