package com.raywenderlich.android.coffeelogs

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class CoffeeLoggerWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
//        // There may be multiple widgets active, so update all of them
//        for (appWidgetId in appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId)
//        }
        val intent = Intent(context.applicationContext, CoffeeQuotesService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        context.startService(intent)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val coffeeLoggerPersistence = CoffeeLoggerPersistence(context)
            val widgetText = coffeeLoggerPersistence.loadTitlePref().toString()

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.coffee_logger_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            views.setOnClickPendingIntent(
                R.id.ristretto_button,
                getPendingIntent(context, CoffeeTypes.RISTRETTO.grams)
            )
            views.setOnClickPendingIntent(
                R.id.ristretto_button,
                getPendingIntent(context, CoffeeTypes.ESPRESSO.grams)
            )
            views.setOnClickPendingIntent(
                R.id.ristretto_button,
                getPendingIntent(context, CoffeeTypes.LONG.grams)
            )

            views.setTextViewText(R.id.coffee_quote, getRandomQuote(context))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getRandomQuote(context: Context): String {
            //1
            val quotes = context.resources.getStringArray(R.array.coffee_texts)
            //2
            val rand = Math.random() * quotes.size
            //3
            return quotes[rand.toInt()].toString()
        }

        private fun getPendingIntent(context: Context, value: Int): PendingIntent {
            //1
            val intent = Intent(context, MainActivity::class.java)
            //2
            intent.action = Constants.ADD_COFFEE_INTENT
            //3
            intent.putExtra(Constants.GRAMS_EXTRA, value)
            //4
            return PendingIntent.getActivity(context, value, intent, 0)


        }
    }
}