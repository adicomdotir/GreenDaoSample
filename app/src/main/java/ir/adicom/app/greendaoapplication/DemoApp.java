package ir.adicom.app.greendaoapplication;

import android.app.Application;

import ir.adicom.app.greendaoapplication.Models.DaoMaster;
import ir.adicom.app.greendaoapplication.Models.DaoSession;
import ir.adicom.app.greendaoapplication.Models.Event;

public class DemoApp extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo4.db").getWritableDb())
                .newSession();

        if(mDaoSession.getEventDao().loadAll().size() == 0){
            mDaoSession.getEventDao().insert(new Event(1L, "Soccer"));
        }
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}