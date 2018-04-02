package com.android.project.moviebox.BaseClass;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.android.project.moviebox.Interfaces.IView;

/**
 * Base activity to perform any action w.r.t base activity context
 */
public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity implements IView {

    protected T viewModel;
    public ProgressDialog progressDlg;

    // API to show progress with custom message.
    public void showProgress(String info) {

        if (progressDlg != null && progressDlg.isShowing()) {
            return;
        }

        if (progressDlg == null && !isFinishing()) {
            try {
                progressDlg = ProgressDialog.show(this, null, info, true, false);
                progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // API to stop progress
    public void stopProgress() {
        try {
            if (progressDlg != null && !isFinishing()) {
                progressDlg.dismiss();
                progressDlg = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.clearSubscriptions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.detach();
        progressDlg = null;
    }
}
