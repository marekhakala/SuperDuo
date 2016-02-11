package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;

public class FixtureResultEntity {
    @Expose
    private Long goalsHomeTeam;
    @Expose
    private Long goalsAwayTeam;

    public Long getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public FixtureResultEntity setGoalsHomeTeam(Long goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
        return this;
    }

    public Long getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public FixtureResultEntity setGoalsAwayTeam(Long goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
        return this;
    }
}
