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
@Table(name="user")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.validatePwd", query = "SELECT u FROM User u WHERE u.phone = :phone and u.password = :password"),
	@NamedQuery(name = "User.forgetPwd", query = "UPDATE User u SET u.password= :password WHERE u.phone= :phone"),
	@NamedQuery(name = "User.activateUser", query = "UPDATE User u SET u.active= :active WHERE u.phone= :phone"), })

public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(length=100)
    private String email;
    @Column(nullable=false, length=20)
    private String phone;
    @Column(length=20)
    private String password;
    @Column(nullable=false, length=100)
    private String name;
    @Column(length=1)
    private boolean active;
    @Column(length=1)
    private boolean deleted;
    @Column(length=10)
    private String otp;
    @Column(length=10)
    private String gender;
    private LocalDate dob;
    private LocalDate createdon;
    @Column(length=100)
    private String city;
    @Column(length=100)
    private String country;
    @Column(length=20)
    private String contact;
    @Column(length=500)
    private String deviceid;
    @Column(length=100)
    private String devicename;
    @Column(length=200)
    private String avatar;
    private LocalDate logindate;

    /** Default constructor. */
    public User() {
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
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
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
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
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
     * Access method for active.
     *
     * @return true if and only if active is currently true
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setter method for active.
     *
     * @param aActive the new value for active
     */
    public void setActive(boolean aActive) {
        active = aActive;
    }

    /**
     * Access method for deleted.
     *
     * @return true if and only if deleted is currently true
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Setter method for deleted.
     *
     * @param aDeleted the new value for deleted
     */
    public void setDeleted(boolean aDeleted) {
        deleted = aDeleted;
    }

    /**
     * Access method for otp.
     *
     * @return the current value of otp
     */
    public String getOtp() {
        return otp;
    }

    /**
     * Setter method for otp.
     *
     * @param aOtp the new value for otp
     */
    public void setOtp(String aOtp) {
        otp = aOtp;
    }

    /**
     * Access method for gender.
     *
     * @return the current value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter method for gender.
     *
     * @param aGender the new value for gender
     */
    public void setGender(String aGender) {
        gender = aGender;
    }

    /**
     * Access method for dob.
     *
     * @return the current value of dob
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Setter method for dob.
     *
     * @param aDob the new value for dob
     */
    public void setDob(LocalDate aDob) {
        dob = aDob;
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
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for country.
     *
     * @return the current value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter method for country.
     *
     * @param aCountry the new value for country
     */
    public void setCountry(String aCountry) {
        country = aCountry;
    }

    /**
     * Access method for contact.
     *
     * @return the current value of contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * Setter method for contact.
     *
     * @param aContact the new value for contact
     */
    public void setContact(String aContact) {
        contact = aContact;
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
     * Access method for devicename.
     *
     * @return the current value of devicename
     */
    public String getDevicename() {
        return devicename;
    }

    /**
     * Setter method for devicename.
     *
     * @param aDevicename the new value for devicename
     */
    public void setDevicename(String aDevicename) {
        devicename = aDevicename;
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
     * Access method for logindate.
     *
     * @return the current value of logindate
     */
    public LocalDate getLogindate() {
        return logindate;
    }

    /**
     * Setter method for logindate.
     *
     * @param aLogindate the new value for logindate
     */
    public void setLogindate(LocalDate aLogindate) {
        logindate = aLogindate;
    }

    /**
     * Compares the key for this instance with another User.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class User and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another User.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) return false;
        return this.equalKeys(other) && ((User)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[User |");
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
