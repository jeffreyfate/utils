package com.jeffthefate.utils.json.timezone;

public class TimeZone {

    private long dstOffset;
    private long rawOffset;
    private String status;
    private String timeZoneId;
    private String timeZoneName;

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    public long getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(long dstOffset) {
        this.dstOffset = dstOffset;
    }

    public long getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(long rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        return "dstOffset: " + getDstOffset() + ", rawOffset: " + getRawOffset()
                + ", status: " + getStatus() + ", timeZoneId: " +
                getTimeZoneId() + ", timeZoneName: " + getTimeZoneName();
    }

}
