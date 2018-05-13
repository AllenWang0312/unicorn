package edu.tjrac.swant.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import edu.tjrac.swant.unicorn.R;
import com.yckj.baselib.util.L;

/**
 * Created by LuHao on 2016/9/26.
 */
public class BlueToothHelper {


    /**
     * UUID identified with this app - set as Service UUID for BLE Advertisements.
     * <p>
     * Bluetooth requires a certain format for UUIDs associated with Services.
     * The official specification can be found here:
     * {@link https://www.bluetooth.org/en-us/specification/assigned-numbers/service-discovery}
     */

    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    public static final ParcelUuid Service_UUID = ParcelUuid
            .fromString("0000b81d-0000-1000-8000-00805f9b34fb");

    public static final int REQUEST_ENABLE_BT = 1;

    /**
     * 蓝牙UUID
     */
//    public static final UUID SPP_UUID = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    public static UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    //启用蓝牙
    public static final int BLUE_TOOTH_OPEN = 1000;
    //禁用蓝牙
    public static final int BLUE_TOOTH_CLOSE = BLUE_TOOTH_OPEN + 1;
    //搜索蓝牙
    public static final int BLUE_TOOTH_SEARTH = BLUE_TOOTH_CLOSE + 1;
    //被搜索蓝牙
    public static final int BLUE_TOOTH_MY_SEARTH = BLUE_TOOTH_SEARTH + 1;
    //关闭蓝牙连接
    public static final int BLUE_TOOTH_CLEAR = BLUE_TOOTH_MY_SEARTH + 1;

    public static String getType(int type) {
        switch (type) {
            case 1:
                return "CLASSIC";
            case 2:
                return "BLE only";
            case 3:
                return "CLASSIC and BLE";
            default:
                return "UNKNOWN";
        }
    }

    public static String getStringUUIDs(List<ParcelUuid> serviceUuids) {
        if (serviceUuids != null && serviceUuids.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (ParcelUuid id : serviceUuids) {
                sb.append(id.toString());
                sb.append(",");
            }
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

   public static String[] flagsValue;
    public static String[] keySet;

    public static void initRes(Context context) {
        flagsValue = context.getResources().getStringArray(R.array.bluetooth_service_type_flags);
        keySet = context.getResources().getStringArray(R.array.scan_info_key_list);

    }

    public static String getFlags( boolean[] flags) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < flags.length; i++) {
            if (flags[5 - i]) {
                sb.append(flagsValue[i]);
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return "";
        }

    }

    public static boolean[] getFlags(int advertiseFlags) {
//        int flag = advertiseFlags;
        int a = 0x000001;
        int i1 = advertiseFlags;
        boolean[] flags = new boolean[6];
        for (int i = 0; i < flags.length; i++) {
            flags[5 - i] = (a & i1) == 0x01;
            i1 = i1 >> 1;
            L.i("getFlags:" + (5 - i), String.valueOf(flags[5 - i]));
        }
        L.i("getFlags: result = " + String.valueOf(advertiseFlags),
                String.valueOf(flags));
        return flags;
    }

    public static void createBond(BluetoothDevice device) {
        Method createBondMethod;
        try {
            createBondMethod = BluetoothDevice.class
                    .getMethod("createBond");
            createBondMethod.invoke(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
