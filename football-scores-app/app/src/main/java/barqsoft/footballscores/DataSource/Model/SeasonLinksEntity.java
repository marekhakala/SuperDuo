package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;

public class SeasonLinksEntity {
    @Expose
    private HrefEntity self;
    @Expose
    private HrefEntity teams;
    @Expose
    private HrefEntity fixtures;
    @Expose
    private HrefEntity leagueTable;

    public HrefEntity getSelf() {
        return self;
    }

    public SeasonLinksEntity setSelf(HrefEntity self) {
        this.self = self;
        return this;
    }

    public HrefEntity getTeams() {
        return teams;
    }

    public SeasonLinksEntity setTeams(HrefEntity teams) {
        this.teams = teams;
        return this;
    }

    public HrefEntity getFixtures() {
        return fixtures;
    }

    public SeasonLinksEntity setFixtures(HrefEntity fixtures) {
        this.fixtures = fixtures;
        return this;
    }

    public HrefEntity getLeagueTable() {
        return leagueTable;
    }

    public SeasonLinksEntity setLeagueTable(HrefEntity leagueTable) {
        this.leagueTable = leagueTable;
        return this;
    }
}
