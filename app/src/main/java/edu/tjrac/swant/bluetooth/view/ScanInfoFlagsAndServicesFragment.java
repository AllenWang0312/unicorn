package edu.tjrac.swant.bluetooth.view;

import android.annotation.SuppressLint;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.widget.BlueToothFlagsView;

/**
 * Created by wpc on 2018/2/2 0002.
 */

@SuppressLint("ValidFragment")
public class ScanInfoFlagsAndServicesFragment extends Fragment {

    private final ParcelUuid[] mUuids;
    private final int mAdvertiseFlags;
    @BindView(R.id.ll_services) LinearLayout mLlServices;
    Unbinder unbinder;
    @BindView(R.id.flag_view) BlueToothFlagsView mFlagView;

    public ScanInfoFlagsAndServicesFragment(ScanResult scanResult) {
        mUuids = scanResult.getDevice().getUuids();
        mAdvertiseFlags = scanResult.getScanRecord().getAdvertiseFlags();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flags_service, container, false);
        unbinder = ButterKnife.bind(this, view);

        mFlagView.setFlag(mAdvertiseFlags);

        if (mUuids != null && mUuids.length > 0) {
            for (ParcelUuid uuid : mUuids) {
                TextView textView = new TextView(getActivity());
                textView.setText(uuid.toString());
                mLlServices.addView(textView);
            }
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
