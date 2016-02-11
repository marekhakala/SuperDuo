package barqsoft.footballscores.Utilities;

import android.content.res.Resources;

import barqsoft.footballscores.R;

public class UtilityHelper {
    public static final int BUNDESLIGA1 = 394;
    public static final int BUNDESLIGA2 = 395;
    public static final int BUNDESLIGA3 = 403;
    public static final int LIGUE1 = 396;
    public static final int LIGUE2 = 397;
    public static final int PREMIER_LEAGUE = 398;
    public static final int PRIMERA_DIVISION = 399;
    public static final int SEGUNDA_DIVISION = 400;
    public static final int SERIE_A = 401;
    public static final int PRIMEIRA_LIGA = 402;
    public static final int EREDIVISIE = 404;
    public static final int CHAMPIONS_LEAGUE = 405;
    public static final int LEAGUE_ONE = 425;

    public static String getLeague(Resources resources, int leagueId) {
        switch (leagueId) {
            case BUNDESLIGA1:
                return resources.getString(R.string.bundesliga1);
            case BUNDESLIGA2:
                return resources.getString(R.string.bundesliga2);
            case BUNDESLIGA3:
                return resources.getString(R.string.bundesliga3);
            case LIGUE1:
                return resources.getString(R.string.ligue1);
            case LIGUE2:
                return resources.getString(R.string.ligue2);
            case PREMIER_LEAGUE:
                return resources.getString(R.string.premierleague);
            case PRIMERA_DIVISION:
                return resources.getString(R.string.primeradivison);
            case SEGUNDA_DIVISION:
                return resources.getString(R.string.segunda_division);
            case SERIE_A:
                return resources.getString(R.string.seriaa);
            case PRIMEIRA_LIGA:
                return resources.getString(R.string.primeira_liga);
            case EREDIVISIE:
                return resources.getString(R.string.eredivise);
            case CHAMPIONS_LEAGUE:
                return resources.getString(R.string.champions_league);
            case LEAGUE_ONE:
                return resources.getString(R.string.league_one);
            default:
                return resources.getString(R.string.unknown_league);
        }
    }

    public static String getMatchDay(int matchDay, int leagueId) {
        if (leagueId == CHAMPIONS_LEAGUE) {
            if (matchDay <= 6) {
                return "Group Stages, Matchday : 6";
            } else if (matchDay == 7 || matchDay == 8) {
                return "First Knockout round";
            } else if (matchDay == 9 || matchDay == 10) {
                return "QuarterFinal";
            } else if (matchDay == 11 || matchDay == 12) {
                return "SemiFinal";
            } else {
                return "Final";
            }
        } else {
            return "Matchday : " + String.valueOf(matchDay);
        }
    }

    public static String getScores(long homeGoals, long awayGoals) {
        if (homeGoals < 0 || awayGoals < 0)
            return " - ";
        else
            return String.valueOf(homeGoals) + " - " + String.valueOf(awayGoals);
    }

    public static int getTeamCrestByTeamName(String teamName) {
        if (teamName == null)
            return R.drawable.no_icon;

        switch (teamName) {
            case "Manchester United FC":
                return R.drawable.manchester_united;
            case "Tottenham Hotspur FC":
                return R.drawable.tottenham_hotspur;
            case "AFC Bournemouth":
                return R.drawable.bournemouth_afc;
            case "Aston Villa FC":
                return R.drawable.aston_villa;
            case "Everton FC":
                return R.drawable.everton_fc_logo1;
            case "Watford FC":
                return R.drawable.watford;
            case "Leicester City FC":
                return R.drawable.leicester_city_fc_hd_logo;
            case "Sunderland AFC":
                return R.drawable.sunderland;
            case "Norwich City FC":
                return R.drawable.norwich_city_fc;
            case "Crystal Palace FC":
                return R.drawable.crystal_palace_fc;
            case "Chelsea FC":
                return R.drawable.chelsea;
            case "Swansea City FC":
                return R.drawable.swansea_city_afc;
            case "Newcastle United FC":
                return R.drawable.newcastle_united;
            case "Southampton FC":
                return R.drawable.southampton_fc;
            case "Arsenal FC":
                return R.drawable.arsenal;
            case "West Ham United FC":
                return R.drawable.west_ham;
            case "Stoke City FC":
                return R.drawable.stoke_city;
            case "Liverpool FC":
                return R.drawable.liverpool;
            case "West Bromwich Albion FC":
                return R.drawable.west_bromwich_albion_hd_logo;
            case "Manchester City FC":
                return R.drawable.manchester_city;
            case "Hull City AFC":
                return R.drawable.hull_city_afc_hd_logo;
            default:
                return R.drawable.no_icon;
        }
    }
}
