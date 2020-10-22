package net.learn2develop.jelovnikaplikacija;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;

public class DialogAbout extends AlertDialog.Builder {

    protected DialogAbout(@NonNull final Context context) {
        super(context);
        setTitle("Kisker");
        setMessage("By Sveto");
        setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public AlertDialog prepareDialog() {
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}
