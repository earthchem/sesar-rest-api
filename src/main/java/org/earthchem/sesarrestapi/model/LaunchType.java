package org.earthchem.sesarrestapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.earthchem.sesarrestapi.dao.LaunchTypeDAO;


/**
 * The persistent class for the launch_type database table.
 * 
 */
@Entity
@Table(name="launch_type")
@NamedQuery(name="LaunchType.findAll", query="SELECT l FROM LaunchType l")
public class LaunchType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="launch_type_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="launchtype_seq")	
	@SequenceGenerator(name = "launchtype_seq", sequenceName = "launch_type_launch_type_id_seq",allocationSize=1)
	private Integer launchTypeId;

	private String description;

	private String name;

	//bi-directional many-to-one association to Sample
	@OneToMany(mappedBy="launchType")
	private List<Sample> samples;

	public LaunchType() {
	}

	public Integer getLaunchTypeId() {
		return this.launchTypeId;
	}

	public void setLaunchTypeId(Integer launchTypeId) {
		this.launchTypeId = launchTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sample> getSamples() {
		return this.samples;
	}

	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}

	public Sample addSample(Sample sample) {
		getSamples().add(sample);
		sample.setLaunchType(this);

		return sample;
	}

	public Sample removeSample(Sample sample) {
		getSamples().remove(sample);
		sample.setLaunchType(null);

		return sample;
	}

	public LaunchTypeDAO getDAO()
	{
		LaunchTypeDAO a = new LaunchTypeDAO();
		a.setLaunchTypeId(this.launchTypeId);
		a.setDescription(this.description);
		a.setName(this.name);		
		return a;
	}
}