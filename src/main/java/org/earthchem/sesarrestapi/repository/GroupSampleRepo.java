package org.earthchem.sesarrestapi.repository;

import java.util.List;

import org.earthchem.sesarrestapi.model.GroupSample;
import org.earthchem.sesarrestapi.model.Sample;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSampleRepo extends CrudRepository<GroupSample, Integer> {
	@Query("SELECT e.sample from GroupSample e  where (e.group.isPrivate = false and e.group.id = ?1) ")
	public List<Sample> getSamplesById(@Param("id") Integer id, Pageable pageable);

	@Query("SELECT count(e.sample) from GroupSample e  where (e.group.isPrivate = false and e.group.id = ?1) ")
	public Integer getTotalSamplesById(@Param("id") Integer id);

	@Query("SELECT e.sample from GroupSample e  where (e.group.isPrivate = false and e.group.name = ?1) ")
	public List<Sample> getSamplesByName(@Param("name") String name, Pageable pageable);
	
	@Query("SELECT count(e.sample) from GroupSample e  where (e.group.isPrivate = false and e.group.name = ?1) ")
	public Integer getTotalSamplesByName(@Param("name") String name);	
}