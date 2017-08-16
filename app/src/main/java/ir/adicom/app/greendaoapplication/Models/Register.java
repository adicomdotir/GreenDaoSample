package ir.adicom.app.greendaoapplication.Models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

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
    @ToOne(joinProperty = "provinceId")
    private Province province;
    private Long cityId;
    @ToOne(joinProperty = "cityId")
    private City city;
    private Long eventId;
    @ToOne(joinProperty = "eventId")
    private Event event;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 430725747)
    private transient RegisterDao myDao;
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
    @Generated(hash = 230593767)
    private transient Long province__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1715419885)
    public Province getProvince() {
        Long __key = this.provinceId;
        if (province__resolvedKey == null || !province__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProvinceDao targetDao = daoSession.getProvinceDao();
            Province provinceNew = targetDao.load(__key);
            synchronized (this) {
                province = provinceNew;
                province__resolvedKey = __key;
            }
        }
        return province;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 86838860)
    public void setProvince(Province province) {
        synchronized (this) {
            this.province = province;
            provinceId = province == null ? null : province.getId();
            province__resolvedKey = provinceId;
        }
    }
    @Generated(hash = 1696970556)
    private transient Long city__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 146085099)
    public City getCity() {
        Long __key = this.cityId;
        if (city__resolvedKey == null || !city__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CityDao targetDao = daoSession.getCityDao();
            City cityNew = targetDao.load(__key);
            synchronized (this) {
                city = cityNew;
                city__resolvedKey = __key;
            }
        }
        return city;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1359518110)
    public void setCity(City city) {
        synchronized (this) {
            this.city = city;
            cityId = city == null ? null : city.getId();
            city__resolvedKey = cityId;
        }
    }
    @Generated(hash = 520039006)
    private transient Long event__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1904304788)
    public Event getEvent() {
        Long __key = this.eventId;
        if (event__resolvedKey == null || !event__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EventDao targetDao = daoSession.getEventDao();
            Event eventNew = targetDao.load(__key);
            synchronized (this) {
                event = eventNew;
                event__resolvedKey = __key;
            }
        }
        return event;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 12102314)
    public void setEvent(Event event) {
        synchronized (this) {
            this.event = event;
            eventId = event == null ? null : event.getId();
            event__resolvedKey = eventId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1384296426)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRegisterDao() : null;
    }
}
