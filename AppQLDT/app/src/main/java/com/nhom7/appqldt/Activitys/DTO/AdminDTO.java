package com.nhom7.appqldt.Activitys.DTO;

import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.Admin;

public class AdminDTO {
    private Admin admin;
    private Account account;

    public AdminDTO(Admin admin, Account account) {
        this.admin = admin;
        this.account = account;
    }

    public AdminDTO() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
