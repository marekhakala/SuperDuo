package barqsoft.footballscores;

import javax.inject.Singleton;

import barqsoft.footballscores.DataSource.DataSourceComponent;
import barqsoft.footballscores.DataSource.DataSourceModule;
import barqsoft.footballscores.Database.DatabaseComponent;
import barqsoft.footballscores.Database.DatabaseModule;

import barqsoft.footballscores.Ui.UiComponent;
import barqsoft.footballscores.Ui.UiModule;
import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DataSourceModule.class,
                DatabaseModule.class,
                UiModule.class
        }
)
public interface AppComponent extends DataSourceComponent, DatabaseComponent, UiComponent {
        void inject(FootballScoresApplication application);
}
