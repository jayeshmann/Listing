package com.xtremus.listing.model;

/**
 * Created by Jayesh Mann on 15,July,2019.
 * TSA Pvt. Ltd.
 * jayeshmann06@gmail.com
 */



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class CadetDetail {

    @SerializedName("cadet_name")
    @Expose
    private String cadetName;
    @SerializedName("enrollment_no")
    @Expose
    private String enrollmentNo;
    @SerializedName("is_iso_uploaded")
    @Expose
    private String isIsoUploaded;

    public String getCadetName() {
        return cadetName;
    }

    public void setCadetName(String cadetName) {
        this.cadetName = cadetName;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getIsIsoUploaded() {
        return isIsoUploaded;
    }

    public void setIsIsoUploaded(String isIsoUploaded) {
        this.isIsoUploaded = isIsoUploaded;
    }

    @Override
    public String toString() {
        return "CadetDetail{" +
                "cadetName='" + cadetName + '\'' +
                ", enrollmentNo='" + enrollmentNo + '\'' +
                ", isIsoUploaded='" + isIsoUploaded + '\'' +
                '}';
    }
}