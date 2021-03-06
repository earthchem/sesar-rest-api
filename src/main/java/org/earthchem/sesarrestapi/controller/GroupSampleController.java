package org.earthchem.sesarrestapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.earthchem.sesarrestapi.dao.SampleProfileDAO;
import org.earthchem.sesarrestapi.model.Sample;
import org.earthchem.sesarrestapi.service.GroupSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/v1")
@Api(value="GroupSample", description="Samples from a group in SESAR",  tags = { "GroupSample" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The operation is successfully."),
        @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
        @ApiResponse(code = 403, message = "You try to access the forbidden resource."),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
}
)
public class GroupSampleController {
	
	@Autowired
	private GroupSampleService service;
	
	@ApiOperation(value = "Get a list of sample profile by group id")
	@GetMapping(path= {"/samples/group/id/{id}",
                       "/samples/group/id/{id}/limit/{limit}/pagenum/{pagenum}",
                       "/samples/group/id/{id}/limit/{limit}",
                       "/samples/group/id/{id}/pagenum/{pagenum}"
                       },
	            headers ={"Accept=application/json,application/xml"},
			    produces={"application/json", "application/xml"}
			    )  
	@ResponseBody
	public ResponseEntity<List<SampleProfileDAO>> getById(@PathVariable Integer id,
                                                      @PathVariable(required = false) @ApiParam(value = "Maximum is 10,000") Integer limit,
                                                      @PathVariable(required = false) @ApiParam(value = "start number is 0.") Integer pagenum) {
        if(limit != null && pagenum == null) pagenum = new Integer(0); //Default to first page
        if(limit == null && pagenum != null) limit = new Integer(100); //Default to first page
        if(limit == null && pagenum == null) {limit = new Integer(100);pagenum = new Integer(0);}; //Default to first page


        if(limit.intValue() > 10000)
        {   return new ResponseEntity<List<SampleProfileDAO>>(new ArrayList<SampleProfileDAO>(), HttpStatus.BAD_REQUEST);
        }
        
		List<Sample> sl = service.getPublicSamplesById(id,limit, pagenum);
		if(sl == null || sl.isEmpty())
		{
			return new ResponseEntity<List<SampleProfileDAO>>(new ArrayList<SampleProfileDAO>(), HttpStatus.NOT_FOUND);		
		}
		else
		{
		    List<SampleProfileDAO> sp = new ArrayList<SampleProfileDAO>();
		    for( Sample s: sl)
		    {
		    	sp.add(s.getDAO());
		    }
			return new ResponseEntity<List<SampleProfileDAO> >(sp, HttpStatus.OK);
		}
	}

	
	@ApiOperation(value = "Get a list of sample profile by group name")
	@GetMapping(path= {"/samples/group"},
	            headers ={"Accept=application/json,application/xml"},
			    produces={"application/json", "application/xml"})  
	@ResponseBody
	public ResponseEntity<List<SampleProfileDAO>> getSamplesByName(
			                                                 @RequestParam(required = true) String name,
                                                             @RequestParam(required = false) @ApiParam(value = "Maximum is 10,000") Integer limit,
                                                             @RequestParam(required = false) @ApiParam(value = "start number is 0.") Integer pagenum
                                                             
                                                             ) {
        if(limit != null && pagenum == null) pagenum = new Integer(0); //Default to first page
        if(limit == null && pagenum != null) limit = new Integer(100); //Default to first page
        if(limit == null && pagenum == null) {limit = new Integer(100);pagenum = new Integer(0);}; //Default to first page

        if(limit.intValue() > 10000)
        {   return new ResponseEntity<List<SampleProfileDAO>>(new ArrayList<SampleProfileDAO>(), HttpStatus.BAD_REQUEST);
        }
		List<Sample> sl = service.getSamplesByName(name,limit, pagenum);
		if(sl == null || sl.isEmpty())
		{
			return new ResponseEntity<List<SampleProfileDAO>>(new ArrayList<SampleProfileDAO>(), HttpStatus.NOT_FOUND);		
		}
		else
		{
		    List<SampleProfileDAO> sp = new ArrayList<SampleProfileDAO>();
		    for( Sample s: sl)
		    {
		    	sp.add(s.getDAO());
		    }
			return new ResponseEntity<List<SampleProfileDAO> >(sp, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Get a list of public sample profile by group name")
	@GetMapping(path= {"/samples/downloadcsv/group"})
    public void downloadCSV(@RequestParam(required = true) String name,
                                              @RequestParam(required = false) @ApiParam(value = "Maximum is 10,000") Integer limit,
                                              @RequestParam(required = false) @ApiParam(value = "start number is 0.") Integer pagenum,
                                              HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
        if(limit != null && pagenum == null) pagenum = new Integer(0); //Default to first page
        if(limit == null && pagenum != null) limit = new Integer(100); //Default to first page
        if(limit == null && pagenum == null) {limit = new Integer(100);pagenum = new Integer(0);}; //Default to first page
        String csvFileName = "data_template.csv";


        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);

       // uses the Super CSV API to generate CSV data from the model data
       ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

       String[] header = { "name", "igsn", "latitude", "longitude","latitudeEnd","longitudeEnd",
               "elevation", "elevationUnit", "sampleType", "sampleSubType","material","navigationType",
               "ageMin","ageMax","ageUnit","primaryLocationType","primaryLocationName","depthMin","depthMax","depthScale"};
		List<Sample> sl = service.getSamplesByName(name,limit, pagenum);
		
		if(sl == null || sl.isEmpty())
		{
			csvWriter.writeHeader(header);
		}
		else
		{
			csvWriter.writeHeader(header);
	        if(limit.intValue() > 10000)
	        {   csvWriter.write("error,exceed the maximum 10,000", header); }
	        else 
	        {
	            for( Sample s: sl)
	            {
	    	        csvWriter.write(s.getDAO(),header);
	            }
	        }
	        //String rnt=csvWriter.toString();            
		}
		csvWriter.close();
    }
	
	@ApiOperation(value = "Get total number of public sample profile by group id")
	@GetMapping(path= "/samples/total/group/id/{id}",
			    produces={"application/json", "application/xml"}
			    )  
	@ResponseBody
	public ResponseEntity<String> getTotalSamplesById(@PathVariable Integer id) {
        Integer t = service.getTotalPublicSamplesById(id);
		return new ResponseEntity<String>(t.toString(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get total number of sample profile by group name")
	@GetMapping(path= "/samples/total/group",
			    produces={"application/json", "application/xml"}
			    )  
	@ResponseBody
	public ResponseEntity<String> getTotalSamplesByName(@RequestParam(required = true) String name) {
        Integer t = service.getTotalSamplesByName(name);
		return new ResponseEntity<String>(t.toString(), HttpStatus.OK);
	}

}
