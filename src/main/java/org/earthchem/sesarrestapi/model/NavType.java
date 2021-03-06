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

import org.earthchem.sesarrestapi.dao.NavTypeDAO;


/**
 * The persistent class for the nav_type database table.
 * 
 */
@Entity
@Table(name="nav_type")
@NamedQuery(name="NavType.findAll", query="SELECT n FROM NavType n")
public class NavType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nav_type_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="navtype_seq")	
	@SequenceGenerator(name = "navtype_seq", sequenceName = "nav_type_nav_type_id_seq",allocationSize=1)
	private Integer navTypeId;

	private String description;

	private String name;

	//bi-directional many-to-one association to Sample
	@OneToMany(mappedBy="navType")
	private List<Sample> samples;

	public NavType() {
	}

	public Integer getNavTypeId() {
		return this.navTypeId;
	}

	public void setNavTypeId(Integer navTypeId) {
		this.navTypeId = navTypeId;
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
		sample.setNavType(this);

		return sample;
	}

	public Sample removeSample(Sample sample) {
		getSamples().remove(sample);
		sample.setNavType(null);

		return sample;
	}

	public NavTypeDAO getDAO()
	{
		NavTypeDAO a = new NavTypeDAO();
		a.setNavTypeId(this.navTypeId);
		a.setName(this.name);
		a.setDescription(this.description);
		return a;
	}
}