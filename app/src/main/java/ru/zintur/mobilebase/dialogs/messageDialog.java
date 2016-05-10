package ru.zintur.mobilebase.dialogs;


        import android.app.Activity;
        import android.content.DialogInterface;
        import android.support.v7.app.AlertDialog;

        import ru.zintur.mobilebase.R;
        import ru.zintur.mobilebase.schema.DataSource;

public class MessageDialog {

    public static void showDialog(Activity activity, String title, String message, boolean showIcon) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        if (showIcon)
            builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static void underConstructionMsg(Activity activity) {
        showDialog(
                activity,
                String.format("%s %s",
                        activity.getString(R.string.app_title), activity.getString(R.string.app_version)),
                activity.getString(R.string.txtUnderConstruction), false);
    }



    public static void aboutDialog(Activity activity) {
        showDialog(activity, activity.getString(R.string.txtAbout),
                String.format("%s %s\n%s\n%s : %s\n\n%s",
                        activity.getString(R.string.app_title),
                        activity.getString(R.string.app_version),
                        activity.getString(R.string.app_description),
                        activity.getString(R.string.txtDbVersion),
                        DataSource.getVersion(),
                        activity.getString(R.string.app_author)
                ), true
        );
    }
}