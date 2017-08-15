package ir.adicom.app.greendaoapplication.Models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by adicom on 8/16/17.
 */

@Entity
public class City {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private long provinceId;
    @Generated(hash = 260814980)
    public City(Long id, String title, long provinceId) {
        this.id = id;
        this.title = title;
        this.provinceId = provinceId;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }
}
