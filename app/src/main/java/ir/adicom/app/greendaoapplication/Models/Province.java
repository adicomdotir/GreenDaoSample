package ir.adicom.app.greendaoapplication.Models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by adicom on 8/12/17.
 */

@Entity
public class Province {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    @Generated(hash = 662592167)
    public Province(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    @Generated(hash = 1309009906)
    public Province() {
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
}
