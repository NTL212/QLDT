package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class ManagerAccount implements Serializable {
    private ManagementStaff managementStaff;

    private Account account;

    public ManagerAccount(ManagementStaff managementStaff, Account account) {
        this.managementStaff = managementStaff;
        this.account = account;
    }

    public ManagerAccount() {
    }

    public ManagementStaff getManagementStaff() {
        return managementStaff;
    }

    public void setManagementStaff(ManagementStaff managementStaff) {
        this.managementStaff = managementStaff;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
