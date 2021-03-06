package org.earthchem.sesarrestapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the sesar_user_code database table.
 * 
 */
@Entity
@Table(name="sesar_user_code")
@NamedQuery(name="SesarUserCode.findAll", query="SELECT s FROM SesarUserCode s")
public class SesarUserCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sesar_user_code_seq")	
	@SequenceGenerator(name = "sesar_user_code_seq", sequenceName = "sesar_user_code_id_seq",allocationSize=1)
	private Long id;

	@Column(name="user_code")
	private String userCode;
	
	@Column(name="igsn_count")
	private Long igsnCount;

	@Column(name="is_available")
	private Integer isAvailable;

	@Column(name="is_grandfather_code")
	private Boolean isGrandfatherCode;

	//bi-directional many-to-one association to Sample
	@OneToMany(mappedBy="sesarUserCode")
	private List<Sample> samples;

	//bi-directional many-to-one association to SesarUser
	@ManyToOne
	@JoinColumn(name="sesar_user_id")
	private SesarUser sesarUser;

	public SesarUserCode() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getIgsnCount() {
		return this.igsnCount;
	}

	public void setIgsnCount(Long igsnCount) {
		this.igsnCount = igsnCount;
	}

	public Integer getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Boolean getIsGrandfatherCode() {
		return this.isGrandfatherCode;
	}

	public void setIsGrandfatherCode(Boolean isGrandfatherCode) {
		this.isGrandfatherCode = isGrandfatherCode;
	}

	public List<Sample> getSamples() {
		return this.samples;
	}

	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}

	public Sample addSample(Sample sample) {
		getSamples().add(sample);
		sample.setSesarUserCode(this);

		return sample;
	}

	public Sample removeSample(Sample sample) {
		getSamples().remove(sample);
		sample.setSesarUserCode(null);

		return sample;
	}

	public SesarUser getSesarUser() {
		return this.sesarUser;
	}

	public void setSesarUser(SesarUser sesarUser) {
		this.sesarUser = sesarUser;
	}

}