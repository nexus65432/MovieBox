package com.android.project.moviebox.BaseClass;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.android.project.moviebox.Interfaces.IView;

/**
 * Base fragment to perform any common functionality
 */
public abstract class BaseFragment<F extends BaseViewModel> extends Fragment implements IView {

    protected F fragmentViewModel;

    protected ProgressDialog progressDlg;

    // API to show progress with custom message.
    public void showProgress(String info) {

        if (progressDlg != null && progressDlg.isShowing()) {
            return;
        }

        if (progressDlg == null) {
            try {
                progressDlg = ProgressDialog.show(getContext(), null, info, true, false);
                progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // API to stop progress
    public void stopProgress() {
        try {
            if (progressDlg != null) {
                progressDlg.dismiss();
                progressDlg = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        fragmentViewModel.clearSubscriptions();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentViewModel.detach();
    }

}
