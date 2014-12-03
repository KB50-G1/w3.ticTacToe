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

    /**
     * Forget about all API warnings. This class is never instantiated if the API is not supporting dialogs.
     */

    static DialogEndGame newInstance(String title) {
        DialogEndGame fragment = new DialogEndGame();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This function avoid the app crashing when the activity is destroyed while the dialog is shown.
        setRetainInstance(true);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setPositiveButton(getResources().getString(R.string.play_again),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((GameActivity) getActivity()).endGameDialogYes();
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.see_board),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((GameActivity) getActivity()).endGameDialogNo();
                            }
                        }).create();
    }

    /**
     * Clicking outside the dialog box have the same effect as clicking See board (NO)
     *
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ((GameActivity) getActivity()).endGameDialogNo();
    }
}
