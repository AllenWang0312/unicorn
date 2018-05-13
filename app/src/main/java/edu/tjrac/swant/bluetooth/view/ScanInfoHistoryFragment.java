package edu.tjrac.swant.bluetooth.view;

import android.annotation.SuppressLint;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yckj.baselib.common.views.BaseChartView;
import com.yckj.baselib.util.L;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.BlueToothHelper;
import edu.tjrac.swant.bluetooth.bean.ScanInfo;
import edu.tjrac.swant.bluetooth.adapter.ScanInfoHistoryAdapter;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static edu.tjrac.swant.bluetooth.BlueToothHelper.keySet;

/**
 * Created by wpc on 2018/2/2 0002.
 */

@SuppressLint("ValidFragment")
public class ScanInfoHistoryFragment extends Fragment {

    @BindView(R.id.rssi_view) BaseChartView mRssiView;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    ArrayList<ScanInfo> infos;
    ArrayList<BaseChartView.ChartValue> chartValues;
    ScanInfoHistoryAdapter adapter;
    Unbinder unbinder;
    private ScanResult result;


    ScanInfoHistoryFragment(ScanResult result, ArrayList<ScanInfo> infos) {
        this.infos = infos;
        this.result = result;
    }


    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scan_info_history, container, false);
        unbinder = ButterKnife.bind(this, v);
//        mRssiView.setTitles(32,"TIME(s)","RSSI(dBm)",getActivity().getResources().getColor(R.color.blue));
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        chartValues = getChartValues(infos);

        mRssiView.setStyle(new BaseChartView.StyleBuilder()
                .line(true)
                .dashPath(true)
                .setXInfo(10f, 0f, 10, "TIME(s)", getActivity().getResources().getColor(R.color.blue))
                .setYInfo(-100, 0, 5, "RSSI(dBm)", getActivity().getResources().getColor(R.color.blue))
                .addLine(getActivity().getResources().getColor(R.color.blue),
                        chartValues)
        );
        L.i("chartValue", chartValues.size() + "");
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ScanInfoHistoryAdapter(infos);

        adapter.bindToRecyclerView(mRecycler);
        View headview = LayoutInflater.from(getActivity()).inflate(R.layout.head_scan_infos, null);
        initHeadView(headview);
        adapter.addHeaderView(headview);
        mRecycler.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }


    String[] values;

    private void initHeadView(View headview) {
        values = new String[4];
        values[0] = BlueToothHelper.getType(result.getDevice().getType());
        boolean[] flags = BlueToothHelper.getFlags(result.getScanRecord().getAdvertiseFlags());

        values[1] = BlueToothHelper.getFlags(flags);
        values[2] = BlueToothHelper.getStringUUIDs(result.getScanRecord().getServiceUuids());
        values[3] = result.getDevice().getName();
        LinearLayout ll = (LinearLayout) headview.findViewById(R.id.infos);
        for (int i = 0; i < keySet.length; i++) {
            SpannableStringBuilder span = new SpannableStringBuilder(keySet[i] + values[i]);
            span.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.blue)),
                    0, keySet[i].length(), SPAN_INCLUSIVE_INCLUSIVE);
            TextView textView = new TextView(getActivity());
            textView.setText(span);
            ll.addView(textView);
        }
        ((TextView) headview.findViewById(R.id.div_time)).setText(infos.get(0).getAdvTime() + "ms");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private ArrayList<BaseChartView.ChartValue> getChartValues(ArrayList<ScanInfo> infos) {
        Long base = infos.get(0).getTagTime();
        ArrayList<BaseChartView.ChartValue> values = new ArrayList<>();
        for (ScanInfo info : infos) {
            values.add(new BaseChartView.ChartValue((base - info.getTagTime()) / 1000, info.getDbm()));
        }
        return values;
    }
}
