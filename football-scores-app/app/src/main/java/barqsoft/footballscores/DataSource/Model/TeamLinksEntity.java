package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamLinksEntity {
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

    public TeamLinksEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public TeamLinksEntity setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
        return this;
    }

    public TeamLinksEntity getLinks() {
        return links;
    }

    public TeamLinksEntity setLinks(TeamLinksEntity links) {
        this.links = links;
        return this;
    }
}
