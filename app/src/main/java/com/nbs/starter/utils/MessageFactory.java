package com.nbs.starter.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nbs.starter.R;

/**
 * Created by ghiyatshanif on 3/1/17.
 * purpose : factory for all message-regarding component like toast,snackbar and alert
 */

public class MessageFactory {

    public static ProgressDialog showLoadingDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.show();
//        if (progressDialog.getWindow() != null) {
//            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static ProgressDialog showCancellabeLoadingDialog(String message) {
        ProgressDialog progressDialog = new ProgressDialog(ContextProvider.get());
        progressDialog.setMessage(message);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        return progressDialog;
    }

    public static void showToast(String message) {
        Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String message) {
        Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_LONG).show();
    }

    public static void showSnackbarMessage(View view, String message) {

        Snackbar snackbar = Snackbar.make(view,
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(ContextProvider.get(), R.color.colorAccent));
        snackbar.show();
    }

    public static void showSnackbarMessage(View view, String message, String actionName, View.OnClickListener actionListener) {
        Snackbar snackbar = Snackbar.make(view,
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(ContextProvider.get(), R.color.colorAccent));

        snackbar.setAction(actionName, actionListener);

        snackbar.show();
    }

    public static void showAlertDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }


    public static void showAlertDialog(Context context, String message
            , String positive
            , DialogInterface.OnClickListener positiveListener) {

        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(positive, positiveListener).show();
    }

    public static void showAlertDialog(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showAlertDialog(Context context, String title
            , String message
            , String positive
            , DialogInterface.OnClickListener positiveListener) {

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, positiveListener).show();
    }

    public static void showAlertDialog(Context context, String title
            , String message
            , String positive
            , DialogInterface.OnClickListener positiveListener
            , String negative
            , DialogInterface.OnClickListener negativeListener) {

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, positiveListener)
                .setNegativeButton(negative, negativeListener)
                .show();
    }

    public static void showUniqueAlertDialog(@NonNull Activity context
            , @Nullable String title
            , @NonNull String message
            , @NonNull String positive
            , @NonNull final MyDialogActionListener positiveListener
            , @Nullable String negative
            , @Nullable final MyDialogActionListener negativeListener) {

        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = context.getLayoutInflater().inflate(R.layout.app_alert_dialog, null);

        TextView btnNegative = (TextView) view.findViewById(R.id.btn_negative);
        TextView btnPositive = (TextView) view.findViewById(R.id.btn_positive);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        tvMessage.setText(message);
        btnPositive.setText(positive);

        if (!TextUtils.isEmpty(negative)) {
            btnNegative.setText(negative);
            btnNegative.setVisibility(View.VISIBLE);
        }

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                positiveListener.action();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (negativeListener != null) {
                    negativeListener.action();
                }
            }
        });


    }

    public static void showNotCancelableUniqueAlertDialog(@NonNull Activity context
            , @Nullable String title
            , @NonNull String message
            , @NonNull String positive
            , @NonNull final MyDialogActionListener positiveListener
            , @Nullable String negative
            , @Nullable final MyDialogActionListener negativeListener) {

        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = context.getLayoutInflater().inflate(R.layout.app_alert_dialog, null);

        TextView btnNegative = (TextView) view.findViewById(R.id.btn_negative);
        TextView btnPositive = (TextView) view.findViewById(R.id.btn_positive);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        tvMessage.setText(message);
        btnPositive.setText(positive);

        if (!TextUtils.isEmpty(negative)) {
            btnNegative.setText(negative);
            btnNegative.setVisibility(View.VISIBLE);
        }

        builder.setView(view);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                positiveListener.action();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (negativeListener != null) {
                    negativeListener.action();
                }
            }
        });


    }

    public static void showUniqueAlertDialog(@NonNull Activity context
            , @Nullable String title
            , @NonNull SpannableString message
            , @NonNull SpannableString highlighted
            , @NonNull SpannableString postHighlighted
            , @NonNull String positive
            , @NonNull final MyDialogActionListener positiveListener
            , @Nullable String negative
            , @Nullable final MyDialogActionListener negativeListener) {

        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = context.getLayoutInflater().inflate(R.layout.app_alert_dialog, null);

        TextView btnNegative = (TextView) view.findViewById(R.id.btn_negative);
        TextView btnPositive = (TextView) view.findViewById(R.id.btn_positive);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }

        tvMessage.setText(TextUtils.concat(message, highlighted, postHighlighted));
        btnPositive.setText(positive);

        if (!TextUtils.isEmpty(negative)) {
            btnNegative.setText(negative);
            btnNegative.setVisibility(View.VISIBLE);
        }

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                positiveListener.action();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (negativeListener != null) {
                    negativeListener.action();
                }
            }
        });


    }


    public interface MyDialogActionListener {
        void action();
    }

}