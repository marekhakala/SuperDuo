package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FixtureEntity {
    @Expose
    private Long id;
    @SerializedName("soccerseasonId")
    @Expose
    private Long soccerSeasonId;
    @Expose
    private String date;
    @SerializedName("matchday")
    @Expose
    private Long matchDay;
    @Expose
    private String homeTeamName;
    @Expose
    private Long homeTeamId;
    @Expose
    private String awayTeamName;
    @Expose
    private Long awayTeamId;
    @Expose
    private FixtureResultEntity result;
    @SerializedName("_links")
    @Expose
    private FixtureLinksEntity links;

    public Long getId() {
        return id;
    }

    public FixtureEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getSoccerSeasonId() {
        return soccerSeasonId;
    }

    public FixtureEntity setSoccerSeasonId(Long soccerSeasonId) {
        this.soccerSeasonId = soccerSeasonId;
        return this;
    }

    public String getDate() {
        return date;
    }

    public FixtureEntity setDate(String date) {
        this.date = date;
        return this;
    }

    public Long getMatchDay() {
        return matchDay;
    }

    public FixtureEntity setMatchDay(Long matchDay) {
        this.matchDay = matchDay;
        return this;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public FixtureEntity setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
        return this;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public FixtureEntity setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
        return this;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public FixtureEntity setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
        return this;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public FixtureEntity setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
        return this;
    }

    public FixtureResultEntity getResult() {
        return result;
    }

    public FixtureEntity setResult(FixtureResultEntity result) {
        this.result = result;
        return this;
    }

    public FixtureLinksEntity getLinks() {
        return links;
    }

    public FixtureEntity setLinks(FixtureLinksEntity links) {
        this.links = links;
        return this;
    }
}
