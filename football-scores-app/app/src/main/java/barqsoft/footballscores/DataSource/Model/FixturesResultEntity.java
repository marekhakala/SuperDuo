package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class FixturesResultEntity {
    @Expose
    private String timeFrameStart;
    @Expose
    private String timeFrameEnd;
    @Expose
    private List<FixtureEntity> fixtures = new ArrayList<FixtureEntity>();
    @Expose
    private Long count;

    public String getTimeFrameStart() {
        return timeFrameStart;
    }

    public FixturesResultEntity setTimeFrameStart(String timeFrameStart) {
        this.timeFrameStart = timeFrameStart;
        return this;
    }

    public String getTimeFrameEnd() {
        return timeFrameEnd;
    }

    public FixturesResultEntity setTimeFrameEnd(String timeFrameEnd) {
        this.timeFrameEnd = timeFrameEnd;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public FixturesResultEntity setCount(Long count) {
        this.count = count;
        return this;
    }

    public List<FixtureEntity> getFixtures() {
        return fixtures;
    }

    public FixturesResultEntity setFixtures(List<FixtureEntity> fixtures) {
        this.fixtures = fixtures;
        return this;
    }
}
