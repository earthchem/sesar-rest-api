package org.earthchem.sesarrestapi.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the sample_delete_request database table.
 * 
 */
@Entity
@Table(name="sample_delete_request")
@NamedQuery(name="SampleDeleteRequest.findAll", query="SELECT s FROM SampleDeleteRequest s")
public class SampleDeleteRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sesar_delete_seq")	
	@SequenceGenerator(name = "sesar_delete_seq", sequenceName = "sample_delete_request_id_seq",allocationSize=1)
	private Long id;

	@Column(name="deactivated_by")
	private Integer deactivatedBy;

	@Column(name="deactivated_date")
	private Timestamp deactivatedDate;

	@Column(name="delete_reason")
	private String deleteReason;

	@Column(name="deleted_by")
	private Integer deletedBy;

	@Column(name="deleted_date")
	private Timestamp deletedDate;

	@Column(name="duplicate_igsns")
	private String duplicateIgsns;

	@Column(name="other_reason")
	private String otherReason;

	@Column(name="requestor_user_id")
	private Integer requestorUserId;

	@Column(name="sample_id")
	private Integer sampleId;

	public SampleDeleteRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDeactivatedBy() {
		return this.deactivatedBy;
	}

	public void setDeactivatedBy(Integer deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}

	public Timestamp getDeactivatedDate() {
		return this.deactivatedDate;
	}

	public void setDeactivatedDate(Timestamp deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}

	public String getDeleteReason() {
		return this.deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public Integer getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Timestamp getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Timestamp deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getDuplicateIgsns() {
		return this.duplicateIgsns;
	}

	public void setDuplicateIgsns(String duplicateIgsns) {
		this.duplicateIgsns = duplicateIgsns;
	}

	public String getOtherReason() {
		return this.otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public Integer getRequestorUserId() {
		return this.requestorUserId;
	}

	public void setRequestorUserId(Integer requestorUserId) {
		this.requestorUserId = requestorUserId;
	}

	public Integer getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

}