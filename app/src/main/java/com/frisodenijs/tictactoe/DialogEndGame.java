package com.frisodenijs.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by alfonsopidal on 27/11/14.
 */
public class DialogEndGame extends DialogFragment {

    static DialogEndGame newInstance(String title) {
        DialogEndGame fragment = new DialogEndGame();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((GameActivity) getActivity()).endGameDialogYes();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((GameActivity) getActivity()).endGameDialogNo();
                            }
                        }).create();
    }


    /**
     * Clicking outside the dialog box = clicking No
     *
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ((GameActivity) getActivity()).endGameDialogNo();
    }
}
