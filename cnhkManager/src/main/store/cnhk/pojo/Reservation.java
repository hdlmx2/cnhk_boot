package store.cnhk.pojo;

import java.sql.Date;
import java.sql.Timestamp;


public class Reservation {
    private int id;
    private String userName;
    private String phoneNumber;
    private Date reservationDate;
    private Short isArrivalsStore;
    private Timestamp operateTime;
    private ServiceTimeSection serviceTimeSection;
    private CnhkProduct cnhkproduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }


    public Short getIsArrivalsStore() {
        return isArrivalsStore;
    }

    public void setIsArrivalsStore(Short isArrivalsStore) {
        this.isArrivalsStore = isArrivalsStore;
    }


    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    public ServiceTimeSection getServiceTimeSection() {
        return serviceTimeSection;
    }

    public void setServiceTimeSection(ServiceTimeSection serviceTimeSection) {
        this.serviceTimeSection = serviceTimeSection;
    }

    public CnhkProduct getCnhkproduct() {
        return cnhkproduct;
    }

    public void setCnhkproduct(CnhkProduct cnhkproduct) {
        this.cnhkproduct = cnhkproduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (reservationDate != null ? !reservationDate.equals(that.reservationDate) : that.reservationDate != null)
            return false;
        if (isArrivalsStore != null ? !isArrivalsStore.equals(that.isArrivalsStore) : that.isArrivalsStore != null)
            return false;
        if (operateTime != null ? !operateTime.equals(that.operateTime) : that.operateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (reservationDate != null ? reservationDate.hashCode() : 0);
        result = 31 * result + (isArrivalsStore != null ? isArrivalsStore.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        return result;
    }
}
