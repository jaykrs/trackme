// Generated with g9.

package com.teqto.trackme.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="userlocation")
public class Userlocation implements Serializable {

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
    @Column(nullable=false, length=20)
    private String latitude;
    @Column(nullable=false, length=20)
    private String longitude;
    @Column(nullable=false)
    private LocalDateTime createdon;
    @Column(nullable=false, length=20)
    private String deviceid;

    /** Default constructor. */
    public Userlocation() {
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
     * Access method for latitude.
     *
     * @return the current value of latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Setter method for latitude.
     *
     * @param aLatitude the new value for latitude
     */
    public void setLatitude(String aLatitude) {
        latitude = aLatitude;
    }

    /**
     * Access method for longitude.
     *
     * @return the current value of longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Setter method for longitude.
     *
     * @param aLongitude the new value for longitude
     */
    public void setLongitude(String aLongitude) {
        longitude = aLongitude;
    }

    /**
     * Access method for createdon.
     *
     * @return the current value of createdon
     */
    public LocalDateTime getCreatedon() {
        return createdon;
    }

    /**
     * Setter method for createdon.
     *
     * @param aCreatedon the new value for createdon
     */
    public void setCreatedon(LocalDateTime aCreatedon) {
        createdon = aCreatedon;
    }

    /**
     * Access method for deviceid.
     *
     * @return the current value of deviceid
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * Setter method for deviceid.
     *
     * @param aDeviceid the new value for deviceid
     */
    public void setDeviceid(String aDeviceid) {
        deviceid = aDeviceid;
    }

    /**
     * Compares the key for this instance with another Userlocation.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Userlocation and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Userlocation)) {
            return false;
        }
        Userlocation that = (Userlocation) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Userlocation.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Userlocation)) return false;
        return this.equalKeys(other) && ((Userlocation)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Userlocation |");
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
