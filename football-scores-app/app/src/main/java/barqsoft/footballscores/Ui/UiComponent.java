package barqsoft.footballscores.Ui;

import barqsoft.footballscores.Ui.Fragment.PagerFragment;
import barqsoft.footballscores.Ui.Fragment.ScoresListFragment;

public interface UiComponent {
    void inject(ScoresListFragment fragment);
    void inject(PagerFragment fragment);
}
