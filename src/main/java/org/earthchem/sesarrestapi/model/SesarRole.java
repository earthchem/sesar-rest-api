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


/**
 * The persistent class for the sesar_role database table.
 * 
 */
@Entity
@Table(name="sesar_role")
@NamedQuery(name="SesarRole.findAll", query="SELECT s FROM SesarRole s")
public class SesarRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sesar_role_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sesar_role_seq")	
	@SequenceGenerator(name = "sesar_role_seq", sequenceName = "sesar_role_sesar_role_id_seq",allocationSize=1)

	private Integer sesarRoleId;

	@Column(name="sesar_role_description")
	private String sesarRoleDescription;

	@Column(name="sesar_role_name")
	private String sesarRoleName;

	//bi-directional many-to-one association to SesarUserCodeRole
	@OneToMany(mappedBy="sesarRole")
	private List<SesarUserCodeRole> sesarUserCodeRoles;

	public SesarRole() {
	}

	public Integer getSesarRoleId() {
		return this.sesarRoleId;
	}

	public void setSesarRoleId(Integer sesarRoleId) {
		this.sesarRoleId = sesarRoleId;
	}

	public String getSesarRoleDescription() {
		return this.sesarRoleDescription;
	}

	public void setSesarRoleDescription(String sesarRoleDescription) {
		this.sesarRoleDescription = sesarRoleDescription;
	}

	public String getSesarRoleName() {
		return this.sesarRoleName;
	}

	public void setSesarRoleName(String sesarRoleName) {
		this.sesarRoleName = sesarRoleName;
	}

	public List<SesarUserCodeRole> getSesarUserCodeRoles() {
		return this.sesarUserCodeRoles;
	}

	public void setSesarUserCodeRoles(List<SesarUserCodeRole> sesarUserCodeRoles) {
		this.sesarUserCodeRoles = sesarUserCodeRoles;
	}

	public SesarUserCodeRole addSesarUserCodeRole(SesarUserCodeRole sesarUserCodeRole) {
		getSesarUserCodeRoles().add(sesarUserCodeRole);
		sesarUserCodeRole.setSesarRole(this);

		return sesarUserCodeRole;
	}

	public SesarUserCodeRole removeSesarUserCodeRole(SesarUserCodeRole sesarUserCodeRole) {
		getSesarUserCodeRoles().remove(sesarUserCodeRole);
		sesarUserCodeRole.setSesarRole(null);

		return sesarUserCodeRole;
	}

}