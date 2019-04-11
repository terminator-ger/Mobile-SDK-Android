package com.dji.sdk.sample.demo.gimbal;

import android.app.Service;
import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.dji.sdk.sample.R;
import com.dji.sdk.sample.internal.utils.DialogUtils;
import com.dji.sdk.sample.internal.utils.ToastUtils;
import com.dji.sdk.sample.internal.view.PresentableView;

import dji.common.error.DJIError;
import dji.common.gimbal.CapabilityKey;
import dji.common.gimbal.GimbalMode;
import dji.common.util.CommonCallbacks;
import dji.common.util.DJIParamCapability;
import dji.sdk.base.BaseProduct;
import dji.sdk.gimbal.Gimbal;
import dji.sdk.products.Aircraft;
import dji.sdk.sdkmanager.DJISDKManager;

import java.util.List;



public class DisableGimbal extends LinearLayout implements View.OnClickListener, PresentableView {

    private List<Gimbal> gimbals = null;
    private BaseProduct product = null;
    private Button btnFree1, btnFree2;
    private Button btnFPV1, btnFPV2;
    private Button btnYaw1, btnYaw2;
    private Button btnMotor1, btnMotor2;
    private Button btnUnknown1, btnUnknown2;


    public DisableGimbal(Context context) {
        super(context);
        initUI(context);
        checkMovementSettingsSupport();

    }

    private void checkMovementSettingsSupport(){
        getGimbal(0);
        for(Gimbal gimbal : gimbals){
            DJIParamCapability capability = null;
            if (gimbal.getCapabilities() != null) {
                capability = gimbal.getCapabilities().get(CapabilityKey.MOVEMENT_SETTINGS);
            }
            if (capability != null) {
                ToastUtils.setResultToToast("Gimbal "+ gimbal.toString() +" MOVEMENT_SETTINGS supported: " + capability.isSupported());
            }
        }
    }

    @NonNull
    @Override
    public String getHint() {
        return this.getClass().getSimpleName() + ".java";
    }


    @Override
    public int getDescription() {
        return R.string.gimbal_remove;
    }

    private Aircraft getAircraft(){
        if(product==null){
            product = DJISDKManager.getInstance().getProduct();
            ToastUtils.setResultToToast("Model: " + product.getModel().name());
        }
        return (Aircraft) product;
    }


    private Gimbal getGimbal(int index) {
        if (gimbals == null) {
            Aircraft aircraft = getAircraft();
            if(aircraft != null) {
                gimbals = aircraft.getGimbals();
            }else{
                ToastUtils.setResultToToast("No Aircraft found");
            }
        }
        if (gimbals != null) {
            Gimbal gimbal = gimbals.get(index);
            return gimbal;

        }else{
            ToastUtils.setResultToToast("No gimbals found");
            return null;
        }
    }



    private void initUI(Context context) {
        setClickable(true);
        setOrientation(HORIZONTAL);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);

        layoutInflater.inflate(R.layout.view_gimbal_disable, this, true);

        btnFree1 = (Button) findViewById(R.id.btn_free_1);
        btnFree2 = (Button) findViewById(R.id.btn_free_2);
        btnFPV1 = (Button) findViewById(R.id.btn_fpv_1);
        btnFPV2 = (Button) findViewById(R.id.btn_fpv_2);
        btnYaw1 = (Button) findViewById(R.id.btn_yaw_1);
        btnYaw2 = (Button) findViewById(R.id.btn_yaw_2);
        btnMotor1 = (Button) findViewById(R.id.btn_motor_1);
        btnMotor2 = (Button) findViewById(R.id.btn_motor_2);
        btnUnknown1 = (Button) findViewById(R.id.btn_unknown_1);
        btnUnknown2 = (Button) findViewById(R.id.btn_unknown_2);


        btnFree1.setOnClickListener(this);
        btnFree2.setOnClickListener(this);
        btnFPV1.setOnClickListener(this);
        btnFPV2.setOnClickListener(this);
        btnYaw1.setOnClickListener(this);
        btnYaw2.setOnClickListener(this);
        btnMotor1.setOnClickListener(this);
        btnMotor2.setOnClickListener(this);
        btnUnknown1.setOnClickListener(this);
        btnUnknown2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_free_1: {
                if(getGimbal(0) != null){
                    getGimbal(0).setMode(GimbalMode.FREE, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_free_2: {
                if(getGimbal(1) !=null) {
                    getGimbal(1).setMode(GimbalMode.FREE, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_fpv_1: {
                if(getGimbal(0) !=null) {
                    getGimbal(0).setMode(GimbalMode.FPV, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
            }
            case R.id.btn_fpv_2: {
                if(getGimbal(1)!=null) {
                    getGimbal(1).setMode(GimbalMode.FPV, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_yaw_1: {
                if(getGimbal(0)!=null) {
                    getGimbal(0).setMode(GimbalMode.YAW_FOLLOW, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_yaw_2: {
                if(getGimbal(1)!=null) {
                    getGimbal(1).setMode(GimbalMode.YAW_FOLLOW, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_motor_1: {
                if(getGimbal(0)!=null) {
                    getGimbal(0).setMotorEnabled(false, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_motor_2: {
                if(getGimbal(1)!=null) {
                    getGimbal(1).setMotorEnabled(false, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }

            case R.id.btn_unknown_1: {
                if(getGimbal(0)!=null) {
                    getGimbal(0).setMode(GimbalMode.UNKNOWN, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
            case R.id.btn_unknown_2: {
                if(getGimbal(0)!=null) {
                    getGimbal(0).setMode(GimbalMode.UNKNOWN, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            DialogUtils.showDialogBasedOnError(getContext(), djiError);
                        }
                    });
                }
                break;
            }
        }
    }
}
