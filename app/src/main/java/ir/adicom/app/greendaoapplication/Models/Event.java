package ir.adicom.app.greendaoapplication.Models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by adicom on 8/11/17.
 */

@Entity
public class Event {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    @Generated(hash = 409782819)
    public Event(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 344677835)
    public Event() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", Title: " + getName();
    }
}
