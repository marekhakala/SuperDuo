package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class TeamsResultEntity {
    @Expose
    private List<TeamEntity> teams = new ArrayList<TeamEntity>();

    public List<TeamEntity> getTeams() {
        return teams;
    }

    public TeamsResultEntity setTeams(List<TeamEntity> teams) {
        this.teams = teams;
        return this;
    }
}
