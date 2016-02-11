package barqsoft.footballscores.DataSource;

import java.util.List;

import barqsoft.footballscores.DataSource.Model.FixturesResultEntity;
import barqsoft.footballscores.DataSource.Model.SeasonEntity;

import barqsoft.footballscores.DataSource.Model.TeamsResultEntity;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface FootballAPI {
    @GET("/soccerseasons")
    List<SeasonEntity> seasons();

    @GET("/soccerseasons/{id}/teams")
    TeamsResultEntity teams(@Path("id") String id);

    @GET("/fixtures")
    FixturesResultEntity fixtures(@Query("timeFrame") String timeFrame);
}
