package barqsoft.footballscores.Widget;

import barqsoft.footballscores.Database.Model.Score;

public class ScoreItem {
    private long id;
    private String date;
    private String time;
    private String home;
    private String away;
    private String league;
    private String leagueCaption;
    private Long homeGoals;
    private Long awayGoals;
    private String matchId;
    private Long matchDay;

    public ScoreItem() {}

    public ScoreItem(long id) {
        this.id = id;
    }

    public ScoreItem(Score object) {
        this.id = object.getId();
        this.date = object.getDate();
        this.time = object.getTime();
        this.home = object.getHome();
        this.away = object.getAway();
        this.league = object.getLeague();
        this.leagueCaption = object.getLeagueCaption();
        this.homeGoals = object.getHomeGoals();
        this.awayGoals = object.getAwayGoals();
        this.matchId = object.getMatchId();
        this.matchDay = object.getMatchDay();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public Long getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Long homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Long getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Long awayGoals) {
        this.awayGoals = awayGoals;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Long getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(Long matchDay) {
        this.matchDay = matchDay;
    }
}
