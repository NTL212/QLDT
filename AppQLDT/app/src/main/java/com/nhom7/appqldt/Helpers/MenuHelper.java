package com.nhom7.appqldt.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDTDaLamActivity;
import com.nhom7.appqldt.Activitys.GiangVien.DanhSachTVActivity;
import com.nhom7.appqldt.Activitys.GiangVien.DeXuatDTActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ThongTinCaNhanActivity;
import com.nhom7.appqldt.R;

public class MenuHelper {
    public static boolean ChonItem(Context context, MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_registerProject) {
            intent = new Intent(context, ListDeTaiActivity.class);
            context.startActivity(intent);
            return true;
        } else if (id == R.id.action_suggestedProject) {
            intent = new Intent(context, DeXuatDTActivity.class);
            context.startActivity(intent);
            return true;
        } else if (id == R.id.action_doneProject) {
            return true;
        } else if (id == R.id.action_projectMember) {
            intent = new Intent(context, DanhSachTVActivity.class);
            context.startActivity(intent);
            return true;
        } else if (id == R.id.action_logout) {
            DangNhapHelper.DangXuat(context);
            return true;
        }
        else if (id == R.id.action_profile) {
            intent = new Intent(context, ThongTinCaNhanActivity.class);
            context.startActivity(intent);
            return true;
        }
        return false;
    }
}
