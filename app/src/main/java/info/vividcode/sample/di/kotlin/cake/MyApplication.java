package info.vividcode.sample.di.kotlin.cake;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

public class MyApplication extends Application {

    private MyApplicationComponent.ObjectGraph myApplicationComponent;

    public MyApplicationComponent.ObjectGraph getMyApplicationComponent() {
        return myApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplicationComponent = MyApplicationComponent.Companion.create(new MyApplicationComponent.Dependencies() {
            @Override @NonNull
            public Context getApplicationContext() {
                return MyApplication.this;
            }
        });
        Log.d("xxx", "millis: " + myApplicationComponent.getClock().millis());
    }

}
