package com.hurontg.mars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROJECT",
	uniqueConstraints={
	   @UniqueConstraint(columnNames={"RECRUITER_ID", "NAME"}, name="UQ__PROJECT__RECRUITER__NAME")
	}
)
public class Project extends BaseEntity implements Serializable {
	public static final String ACTIVE = "ACTIVE";
	public static final String COMPLETED = "COMPLETED";
	public static final String ABORTED = "ABORTED";
	public static final String PAUSED = "PAUSED";

	private static final long serialVersionUID = -303067606088159L;
	private Long id;

	// LeadCriteria
	private String name;
	private String keywords;
	private AuthUser recruiter;
	private List<Profile> profiles = new ArrayList<Profile>();
	private String status = ACTIVE;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *          the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "KEYWORDS", nullable = false)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "RECRUITER_ID", foreignKey = @ForeignKey(name = "FK__PROJECT_AUTHUSER") )
	public AuthUser getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(AuthUser recruiter) {
		this.recruiter = recruiter;
	}

	@ManyToMany()
	@JoinTable(name = "PROJECT_PROFILE", joinColumns = @JoinColumn(name = "PROJECT_ID", foreignKey = @ForeignKey(name = "FK_PROJECT_PROFILE__PROJECT__PROJECT_ID") ) , inverseJoinColumns = @JoinColumn(name = "PROFILE_ID", foreignKey = @ForeignKey(name = "FK_PROJECT_PROFILE__PROFILE__PROFILE_ID") ) )
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName()).append(" Object {").append(NEW_LINE);
		result.append(" id: ").append(id).append(NEW_LINE);
		result.append(" Name: ").append(name).append(NEW_LINE);
		result.append(" Keywords: ").append(keywords).append(NEW_LINE);
		result.append("}");

		return result.toString();
	}

}
