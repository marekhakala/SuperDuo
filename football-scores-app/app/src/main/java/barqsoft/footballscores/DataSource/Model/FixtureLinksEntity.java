package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FixtureLinksEntity {
    @Expose
    private HrefEntity self;
    @Expose
    @SerializedName("soccerseason")
    private HrefEntity soccerSeason;
    @Expose
    private HrefEntity homeTeam;
    @Expose
    private HrefEntity awayTeam;

    public HrefEntity getSelf() {
        return self;
    }

    public FixtureLinksEntity setSelf(HrefEntity self) {
        this.self = self;
        return this;
    }

    public HrefEntity getSoccerSeason() {
        return soccerSeason;
    }

    public FixtureLinksEntity setSoccerSeason(HrefEntity soccerSeason) {
        this.soccerSeason = soccerSeason;
        return this;
    }

    public HrefEntity getHomeTeam() {
        return homeTeam;
    }

    public FixtureLinksEntity setHomeTeam(HrefEntity homeTeam) {
        this.homeTeam = homeTeam;
        return this;
    }

    public HrefEntity getAwayTeam() {
        return awayTeam;
    }

    public FixtureLinksEntity setAwayTeam(HrefEntity awayTeam) {
        this.awayTeam = awayTeam;
        return this;
    }
}
