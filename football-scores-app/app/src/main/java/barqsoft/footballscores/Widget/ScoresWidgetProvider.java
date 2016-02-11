package barqsoft.footballscores.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import barqsoft.footballscores.Ui.Activity.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.DataSource.FetchService;

public class ScoresWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        for(int id : ids) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_today_scores);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget_today_scores_layout, pendingIntent);

            views.setRemoteAdapter(R.id.widget_today_scores_list, new Intent(context, ScoresRemoteViewsService.class));
            views.setEmptyView(R.id.widget_today_scores_list, R.id.widget_today_scores_empty_text);

            manager.updateAppWidget(id, views);
        }
    }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);

        if(intent.getAction().equals(FetchService.DATA_UPDATED_CODE)) {
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            int[] ids = manager.getAppWidgetIds(new ComponentName(context, getClass()));
            manager.notifyAppWidgetViewDataChanged(ids, R.id.widget_today_scores_list);
        }
    }
}
