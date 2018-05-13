/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.tjrac.swant.bluetooth.view;

import android.annotation.SuppressLint;
import android.bluetooth.le.AdvertiseCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.yckj.baselib.common.base.BaseFragment;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.bluetooth.AdvertiserService;
@SuppressLint("ValidFragment")
public class AdvertiserFragment extends BaseFragment implements View.OnClickListener {


    public AdvertiserFragment(String title) {
        this.title = title;
    }

    private Switch mSwitch;

    private BroadcastReceiver advertisingFailureReceiver = new BroadcastReceiver() {

        /**
         * Receives Advertising error codes from {@code AdvertiserService} and displays error messages
         * to the user. Sets the advertising toggle to 'false.'
         */
        @Override
        public void onReceive(Context context, Intent intent) {

            int errorCode = intent.getIntExtra(AdvertiserService.ADVERTISING_FAILED_EXTRA_CODE, -1);

            mSwitch.setChecked(false);

            String errorMessage = getString(R.string.start_error_prefix);
            switch (errorCode) {
                case AdvertiseCallback.ADVERTISE_FAILED_ALREADY_STARTED:
                    errorMessage += " " + getString(R.string.start_error_already_started);
                    break;
                case AdvertiseCallback.ADVERTISE_FAILED_DATA_TOO_LARGE:
                    errorMessage += " " + getString(R.string.start_error_too_large);
                    break;
                case AdvertiseCallback.ADVERTISE_FAILED_FEATURE_UNSUPPORTED:
                    errorMessage += " " + getString(R.string.start_error_unsupported);
                    break;
                case AdvertiseCallback.ADVERTISE_FAILED_INTERNAL_ERROR:
                    errorMessage += " " + getString(R.string.start_error_internal);
                    break;
                case AdvertiseCallback.ADVERTISE_FAILED_TOO_MANY_ADVERTISERS:
                    errorMessage += " " + getString(R.string.start_error_too_many);
                    break;
                case AdvertiserService.ADVERTISING_TIMED_OUT:
                    errorMessage = " " + getString(R.string.advertising_timedout);
                    break;
                default:
                    errorMessage += " " + getString(R.string.start_error_unknown);
            }

            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advertiser, container, false);
        mSwitch = (Switch) view.findViewById(R.id.advertise_switch);
        mSwitch.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AdvertiserService.running) {
            mSwitch.setChecked(true);
        } else {
            mSwitch.setChecked(false);
        }
        IntentFilter failureFilter = new IntentFilter(AdvertiserService.ADVERTISING_FAILED);
        getActivity().registerReceiver(advertisingFailureReceiver, failureFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(advertisingFailureReceiver);
    }

    private static Intent getServiceIntent(Context c) {
        return new Intent(c, AdvertiserService.class);
    }

    @Override
    public void onClick(View v) {
        // Is the toggle on?
        boolean on = ((Switch) v).isChecked();
        if (on) {
            getActivity().startService(getServiceIntent(getActivity()));
        } else {
            getActivity().stopService(getServiceIntent(getActivity()));
            mSwitch.setChecked(false);
        }
    }

    @Override
    public void onBack() {

    }
}