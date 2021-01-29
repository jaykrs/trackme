// Generated with g9.

package com.teqto.trackme.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
@Repository
@Table(name="usergroup")
@NamedQueries({ @NamedQuery(name = "Usergroup.findAll", query = "SELECT ug FROM Usergroup ug"),
	@NamedQuery(name = "Usergroup.findByOwnerid", query = "SELECT ug FROM Usergroup ug WHERE ug.userid = :ownerid and ug.owner = true"),
	@NamedQuery(name = "Usergroup.findByUserid", query = "SELECT ug FROM Usergroup ug WHERE ug.userid = :userid and ug.approved = true"),
	@NamedQuery(name = "Usergroup.findByGroupid", query = "SELECT ug FROM Usergroup ug WHERE ug.groupid = :groupid and ug.approved = false"),
	@NamedQuery(name = "Usergroup.findAllByGroupid", query = "SELECT ug FROM Usergroup ug WHERE ug.groupid = :groupid"),
	@NamedQuery(name = "Usergroup.findByUserAndGroup", query = "SELECT ug FROM Usergroup ug WHERE ug.groupid = :groupid and ug.userid = :userid and ug.owner = false and ug.approved = false"),
	@NamedQuery(name = "Usergroup.findByGroupOnwerAndUser", query = "SELECT ug FROM Usergroup ug WHERE ug.groupid = :groupid and ug.userid = :userid and ug.owner = true")
 })
public class Usergroup implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private int id;
    @Column(nullable=false, precision=10)
    private int userid;
    @Column(nullable=false, precision=10)
    private int groupid;
    @Column(length=1)
    private boolean approved;
    private LocalDate createdon;
    @Column(nullable=false, precision=10)
    private int createdby;
    @Column(precision=10)
    private int approvedby;
    @Column(length=1)
    private boolean owner;
    @Column(length=100)
    private String username;
    @Column(length=200)
    private String avatar;

    /** Default constructor. */
    public Usergroup() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for userid.
     *
     * @return the current value of userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Setter method for userid.
     *
     * @param aUserid the new value for userid
     */
    public void setUserid(int aUserid) {
        userid = aUserid;
    }

    /**
     * Access method for groupid.
     *
     * @return the current value of groupid
     */
    public int getGroupid() {
        return groupid;
    }

    /**
     * Setter method for groupid.
     *
     * @param aGroupid the new value for groupid
     */
    public void setGroupid(int aGroupid) {
        groupid = aGroupid;
    }

    /**
     * Access method for approved.
     *
     * @return true if and only if approved is currently true
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Setter method for approved.
     *
     * @param aApproved the new value for approved
     */
    public void setApproved(boolean aApproved) {
        approved = aApproved;
    }

    /**
     * Access method for createdon.
     *
     * @return the current value of createdon
     */
    public LocalDate getCreatedon() {
        return createdon;
    }

    /**
     * Setter method for createdon.
     *
     * @param aCreatedon the new value for createdon
     */
    public void setCreatedon(LocalDate aCreatedon) {
        createdon = aCreatedon;
    }

    /**
     * Access method for createdby.
     *
     * @return the current value of createdby
     */
    public int getCreatedby() {
        return createdby;
    }

    /**
     * Setter method for createdby.
     *
     * @param aCreatedby the new value for createdby
     */
    public void setCreatedby(int aCreatedby) {
        createdby = aCreatedby;
    }

    /**
     * Access method for approvedby.
     *
     * @return the current value of approvedby
     */
    public int getApprovedby() {
        return approvedby;
    }

    /**
     * Setter method for approvedby.
     *
     * @param aApprovedby the new value for approvedby
     */
    public void setApprovedby(int aApprovedby) {
        approvedby = aApprovedby;
    }

    /**
     * Compares the key for this instance with another Usergroup.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Usergroup and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Usergroup)) {
            return false;
        }
        Usergroup that = (Usergroup) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Usergroup.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Usergroup)) return false;
        return this.equalKeys(other) && ((Usergroup)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = (int)(getId() ^ (getId()>>>32));
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Usergroup |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Long.valueOf(getId()));
        return ret;
    }

	/**
	 * @return the owner
	 */
	public boolean isOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Usergroup(int userid, int groupid, LocalDate createdon, int createdby) {
		super();
		this.userid = userid;
		this.groupid = groupid;
		this.createdon = createdon;
		this.createdby = createdby;
	}

}
