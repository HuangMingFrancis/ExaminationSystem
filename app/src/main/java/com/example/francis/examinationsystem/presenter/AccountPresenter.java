package com.example.francis.examinationsystem.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IAccountView;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.user.UserModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/18.
 */

public class AccountPresenter extends BasePresenter<IAccountView> {

    UserModel userModel;

    public AccountPresenter() {
        userModel = new UserModel();
    }

    public void updataeUserHead(String fileName, String path) {

        userModel.updateHead(fileName, path)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i("http", "call: " + s);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {

                    }
                });
    }


    public byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
//            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static byte[] imageToByte(String path) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {

            InputStream in = new FileInputStream(path);

            data = new byte[in.available()];

            in.read(data);

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }
}
