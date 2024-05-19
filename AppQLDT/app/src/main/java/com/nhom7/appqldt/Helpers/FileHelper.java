package com.nhom7.appqldt.Helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;

public class FileHelper {
    public static File saveFile(Context context, byte[] fileData, String fileName) {

        // Lưu tệp trong thư mục tải về
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileData);
            Log.d("FileDownloader", "File saved at: " + file.getAbsolutePath());
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void openFile(Context context, File file) {
        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);

        // Đoán MIME type từ phần mở rộng của tệp
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "*/*"; // Nếu không xác định được, sử dụng mime type mặc định
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mimeType);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            context.startActivity(intent);
        } catch (Exception e) {
            // Xử lý trường hợp không có ứng dụng nào có thể mở tệp
            e.printStackTrace();
            showToast(context, "Không thể mở tệp này.");
        }
    }

    public static void showToast(Context context, String message) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
    }
}
