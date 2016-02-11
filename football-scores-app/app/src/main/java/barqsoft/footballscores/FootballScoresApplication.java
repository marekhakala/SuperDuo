package barqsoft.footballscores;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;

import barqsoft.footballscores.DataSource.DataSourceModule;
import barqsoft.footballscores.Database.DatabaseModule;
import barqsoft.footballscores.Ui.UiModule;
import barqsoft.footballscores.Utilities.ConstantValues;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class FootballScoresApplication extends Application {
    protected RefWatcher refWatcher;
    protected AppComponent component;

    public static AppComponent appComponent(Context context) {
        return get(context).getComponent();
    }

    public String getPath() {
        return this.getFilesDir().getAbsolutePath();
    }

    public AppComponent getComponent() {
        return component;
    }

    public static FootballScoresApplication get(Context context) {
        return (FootballScoresApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        setupComponent();
        Timber.plant(new Timber.DebugTree());
    }

    protected void setupComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataSourceModule(new DataSourceModule())
                .databaseModule(new DatabaseModule())
                .uiModule(new UiModule())
                .build();
    }

    public static Realm getDatabaseInstance(Application application) {
        String path = FootballScoresApplication.get(application).getPath();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(new File(path))
                .name(ConstantValues.REALM_DB_FILE)
                .schemaVersion(ConstantValues.DB_SCHEMA_VERSION)
                .build();
        return Realm.getInstance(realmConfig);
    }

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
