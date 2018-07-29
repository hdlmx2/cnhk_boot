package store.cnhk.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_service_time_section", schema = "store/cnhk", catalog = "")
public class ServiceTimeSection {
    private int id;
    private String serviceTimeSection;
    private Short serviceNumber;
    private Timestamp timestamp;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "serviceTimeSection")
    public String getServiceTimeSection() {
        return serviceTimeSection;
    }

    public void setServiceTimeSection(String serviceTimeSection) {
        this.serviceTimeSection = serviceTimeSection;
    }

    @Basic
    @Column(name = "serviceNumber")
    public Short getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(Short serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceTimeSection that = (ServiceTimeSection) o;

        if (id != that.id) return false;
        if (serviceTimeSection != null ? !serviceTimeSection.equals(that.serviceTimeSection) : that.serviceTimeSection != null)
            return false;
        if (serviceNumber != null ? !serviceNumber.equals(that.serviceNumber) : that.serviceNumber != null)
            return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serviceTimeSection != null ? serviceTimeSection.hashCode() : 0);
        result = 31 * result + (serviceNumber != null ? serviceNumber.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
