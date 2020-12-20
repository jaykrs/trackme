// Generated with g9.

package com.teqto.trackme.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name="notification")
public class Notification implements Serializable {

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
    @Column(length=1)
    private boolean priority;
    @Column(length=400)
    private String message;
    @Column(length=200)
    private String attachment;

    /** Default constructor. */
    public Notification() {
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
     * Access method for priority.
     *
     * @return true if and only if priority is currently true
     */
    public boolean isPriority() {
        return priority;
    }

    /**
     * Setter method for priority.
     *
     * @param aPriority the new value for priority
     */
    public void setPriority(boolean aPriority) {
        priority = aPriority;
    }

    /**
     * Access method for message.
     *
     * @return the current value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for message.
     *
     * @param aMessage the new value for message
     */
    public void setMessage(String aMessage) {
        message = aMessage;
    }

    /**
     * Access method for attachment.
     *
     * @return the current value of attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * Setter method for attachment.
     *
     * @param aAttachment the new value for attachment
     */
    public void setAttachment(String aAttachment) {
        attachment = aAttachment;
    }

    /**
     * Compares the key for this instance with another Notification.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Notification and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Notification)) {
            return false;
        }
        Notification that = (Notification) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Notification.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Notification)) return false;
        return this.equalKeys(other) && ((Notification)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Notification |");
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
