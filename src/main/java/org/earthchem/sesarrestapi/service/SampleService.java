/**
 * 
 */
package org.earthchem.sesarrestapi.service;

import java.util.List;
import java.util.Optional;

import org.earthchem.sesarrestapi.model.Sample;
import org.earthchem.sesarrestapi.repository.SampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author song
 *
 */
@Service
public class SampleService {

	@Autowired
	SampleRepo repo;

	public SampleService() {};
		
	public void setSampleRepo( SampleRepo r)
	{ this.repo = r; };
		
	/**
	 * Get a navigation type information from Table 'nav_type' according to id.
	 * @return Sample object.
	 */
	public Sample get(Integer id)
	{
	    Optional<Sample> al = repo.findById(id);
	    return al.get();
	}

	/**
	 * Get a navigation type information from Table 'nav_type' according to navigation type name.
	 * @return Sample object.
	 */
	public Sample getByIGSN(String name)
	{
	    Optional<Sample> al = repo.getByIGSN(name);
	    return al.get();
	}
		
	/**
	 * Get a existing list of platform type from table 'sample' platform_type column.
	 * @return  a list of string.
	 */
	public List<String> getPlatformTypes()
	{
	    return repo.getPlatformTypes() ;
	}

	/**
	 * Get a existing list of collection method from table 'sample' collection_method column.
	 * @return  a list of string.
	 */
	public List<String> getCollectionMethods()
	{
		List<String> rl =  repo.getCollectionMethods() ;
		return rl;
	}

	/**
	 * Get a existing list of physiographic feature from table 'sample' primary_location_type column.
	 * @return  a list of string.
	 */
	public List<String> getPrimaryLocationTypes()
	{
	    return repo.getPrimaryLocationTypes() ;
	}


	/**
	 * Get a existing list of platform name from table 'sample' platform_name column.
	 * @return a list of string.
	 */
	public List<String> getPlatformNames()
	{
		List<String> rl =  repo.getPlatformNames() ;
		return rl;
	}
	
	/**
	 * Get a existing list of cruise or field program names from table 'sample' field_name column.
	 * @return  a list of string.
	 */
	public List<String> getCruiseFieldPrgrm()
	{
		List<String> rl =  repo.getCruiseFieldPrgrm() ;
		return rl;
	}
	
	
	/**
	 * Get a existing list of collector from table 'sample' collector column.
	 * @return  a list of string.
	 */
	public List<String> getCollectors()
	{
		List<String> rl =  repo.getCollectors() ;
		return rl;
	}
	
}
