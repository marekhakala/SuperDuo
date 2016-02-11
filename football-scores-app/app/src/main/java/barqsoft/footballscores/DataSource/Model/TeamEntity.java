package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamEntity {
    @Expose
    private String name;
    @Expose
    private String crestUrl;
    @SerializedName("_links")
    @Expose
    private TeamLinksEntity links;

    public String getName() {
        return name;
    }

    public TeamEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public TeamEntity setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
        return this;
    }

    public TeamLinksEntity getLinks() {
        return links;
    }

    public TeamEntity setLinks(TeamLinksEntity links) {
        this.links = links;
        return this;
    }
}
