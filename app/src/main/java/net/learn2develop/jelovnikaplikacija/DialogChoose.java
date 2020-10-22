package net.learn2develop.jelovnikaplikacija;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;

public class DialogChoose extends AlertDialog.Builder {

    protected DialogChoose(@NonNull final Context context) {
        super(context);
        setTitle("Izaberite");
        setMessage("Sta zelite da dodate/promenite/obrisete?");
        // which znaci pozicija u nizu. Mozemo izabrati opcije, itd, klikom na DialogBox
        setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
        });

        setNegativeButton("NE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
        });
    }

    public AlertDialog prepareDialog() {
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}
