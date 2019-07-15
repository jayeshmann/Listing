package com.xtremus.listing.model;

/**
 * Created by Jayesh Mann on 15,July,2019.
 * TSA Pvt. Ltd.
 * jayeshmann06@gmail.com
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListing {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("cadet_details")
    @Expose
    private List<CadetDetail> cadetDetails = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CadetDetail> getCadetDetails() {
        return cadetDetails;
    }

    public void setCadetDetails(List<CadetDetail> cadetDetails) {
        this.cadetDetails = cadetDetails;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "GetListing{" +
                "status='" + status + '\'' +
                ", cadetDetails=" + cadetDetails +
                ", msg='" + msg + '\'' +
                '}';
    }
}
