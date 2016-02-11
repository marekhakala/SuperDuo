package barqsoft.footballscores.DataSource.Model;

import com.google.gson.annotations.Expose;

public class HrefEntity {
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public HrefEntity setHref(String href) {
        this.href = href;
        return this;
    }
}
