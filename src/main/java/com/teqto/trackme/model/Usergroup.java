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

@Entity(name="usergroup")
public class Usergroup implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private long id;
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

    /** Default constructor. */
    public Usergroup() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(long aId) {
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

}
