package me.caiyuan.spring.web.repository;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * YUAN
 * 7/12/16.
 */
public class Spittle {

    @NotNull
    @Max(Long.MAX_VALUE)
    @Min(0)
    private Long id;

    @NotNull(message = "消息不能为空")
    @Size(min = 5, max = 16, message = "消息长度不符合要求(最小长度5,最大长度16)")
    private String message;

    @NotNull
    private Date time;

    @NotNull
    @Min(0)
    private Double latitude;

    @NotNull
    @Min(0)
    private Double longitude;

    public Spittle() {
        this.id = null;
        this.message = null;
        this.time = null;
    }

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;

        if (that == null || getClass() != that.getClass()) return false;

        Spittle spittle = (Spittle) that;

        return new EqualsBuilder()
                .append(id, spittle.id)
                .append(time, spittle.time)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(time)
                .toHashCode();
    }

}
