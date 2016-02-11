package barqsoft.footballscores.Database;

import android.app.Application;

import barqsoft.footballscores.FootballScoresApplication;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DatabaseModule {
    @Provides
    Realm provideRealm(Application application) {
        return FootballScoresApplication.getDatabaseInstance(application);
    }
}
