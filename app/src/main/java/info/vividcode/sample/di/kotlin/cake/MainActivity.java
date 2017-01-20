package info.vividcode.sample.di.kotlin.cake;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.vividcode.sample.di.kotlin.cake.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityComponent = ((MyApplication) getApplication()).getMyApplicationComponent().createMainActivityComponent(new ActivityContextProvider() {
            @Override @NonNull
            public Context getActivityContext() {
                return MainActivity.this;
            }
        });
        binding.setViewModel(mainActivityComponent.getMainActivityViewModel());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainActivityComponent.getMainActivityPresenter().onStart();
    }

    @Override
    protected void onStop() {
        mainActivityComponent.getMainActivityPresenter().onStop();
        super.onStop();
    }
}
