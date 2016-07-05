package com.example.broadcastproject.broadcast;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.broadcastproject.activity.LoginActivity;
import com.example.broadcastproject.util.CollectionActivity;

/**
 * Created by lzl on 2016/7/5.
 */
public class ForceBroadCastReceive extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        //弹出提示框，并且进行强制进入登录页面
        Toast.makeText(context,"接收到停止强制下线的广播",Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("警告");
        builder.setMessage("您有账号在异地登录，请重新登录");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CollectionActivity.finishAll();//销毁所有的activity
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
