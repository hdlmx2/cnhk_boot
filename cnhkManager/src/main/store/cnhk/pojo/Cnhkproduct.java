package store.cnhk.pojo;

import java.sql.Timestamp;


public class Cnhkproduct {
    private String id;
    private String serviceName;
    private double servicePrice;
    private String serviceDesc;
    private Timestamp timestamp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }


    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }


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

        Cnhkproduct that = (Cnhkproduct) o;

        if (Double.compare(that.servicePrice, servicePrice) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        if (serviceDesc != null ? !serviceDesc.equals(that.serviceDesc) : that.serviceDesc != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        temp = Double.doubleToLongBits(servicePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (serviceDesc != null ? serviceDesc.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
