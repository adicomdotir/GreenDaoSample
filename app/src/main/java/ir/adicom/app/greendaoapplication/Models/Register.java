package ir.adicom.app.greendaoapplication.Models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by adicom on 8/17/17.
 */

@Entity
public class Register {
    @Id(autoincrement = true)
    private Long id;
    private String fullName;
    private Long provinceId;
    private Long cityId;
    private Long eventId;
    @Generated(hash = 2039606576)
    public Register(Long id, String fullName, Long provinceId, Long cityId,
            Long eventId) {
        this.id = id;
        this.fullName = fullName;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.eventId = eventId;
    }
    @Generated(hash = 1726459619)
    public Register() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Long getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    public Long getCityId() {
        return this.cityId;
    }
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
