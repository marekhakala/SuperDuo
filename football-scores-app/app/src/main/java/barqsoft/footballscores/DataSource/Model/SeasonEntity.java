package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeasonEntity {
    @Expose
    private Long id;
    @Expose
    private String caption;
    @Expose
    private String league;
    @Expose
    private String year;
    @Expose
    private Integer numberOfTeams;
    @Expose
    private Integer numberOfGames;
    @Expose
    private String lastUpdated;
    @SerializedName("_links")
    @Expose
    private SeasonLinksEntity links;

    public Long getId() {
        return id;
    }

    public SeasonEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public SeasonEntity setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getLeague() {
        return league;
    }

    public SeasonEntity setLeague(String league) {
        this.league = league;
        return this;
    }

    public String getYear() {
        return year;
    }

    public SeasonEntity setYear(String year) {
        this.year = year;
        return this;
    }

    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    public SeasonEntity setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
        return this;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public SeasonEntity setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
        return this;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public SeasonEntity setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public SeasonLinksEntity getLinks() {
        return links;
    }

    public SeasonEntity setLinks(SeasonLinksEntity links) {
        this.links = links;
        return this;
    }
}
