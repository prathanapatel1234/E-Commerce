package uk.actees.aad.w9521088.ApiCall.View;

import android.content.Context;

public interface IUtopperView {

    Context getContext();
    void enableLoadingBar(Context context,boolean enable);
    void onError(String reason);
}
