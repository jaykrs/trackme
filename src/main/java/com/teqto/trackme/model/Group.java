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

@Entity(name="group")
public class Group implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(nullable=false, length=50)
    private String name;
    @Column(nullable=false, precision=10)
    private int owner;
    @Column(length=20)
    private String phone;
    private LocalDate createdon;
    @Column(length=200)
    private String avatar;

    /** Default constructor. */
    public Group() {
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
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for owner.
     *
     * @return the current value of owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Setter method for owner.
     *
     * @param aOwner the new value for owner
     */
    public void setOwner(int aOwner) {
        owner = aOwner;
    }

    /**
     * Access method for phone.
     *
     * @return the current value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for phone.
     *
     * @param aPhone the new value for phone
     */
    public void setPhone(String aPhone) {
        phone = aPhone;
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
     * Access method for avatar.
     *
     * @return the current value of avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Setter method for avatar.
     *
     * @param aAvatar the new value for avatar
     */
    public void setAvatar(String aAvatar) {
        avatar = aAvatar;
    }

    /**
     * Compares the key for this instance with another Group.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Group and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Group)) {
            return false;
        }
        Group that = (Group) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Group.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Group)) return false;
        return this.equalKeys(other) && ((Group)other).equalKeys(this);
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
        i = getId();
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
        StringBuffer sb = new StringBuffer("[Group |");
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
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
