package com.hnxind.object.Contacts;


/**
 * Created by Administrator on 2015/10/30.
 */
public class Contacts {
    String CONTACT_NAME;
    String CONTACT_NUM;
    int ID;
    String CID;
    String isUpdate;
    boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public String isUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBEIZHU() {
        return BEIZHU;
    }

    public void setBEIZHU(String BEIZHU) {
        this.BEIZHU = BEIZHU;
    }

    String BEIZHU;

    public String getCONTACT_NAME() {
        return CONTACT_NAME;
    }

    public void setCONTACT_NAME(String CONTACT_NAME) {
        this.CONTACT_NAME = CONTACT_NAME;
    }

    public String getCONTACT_NUM() {
        return CONTACT_NUM;
    }

    public void setCONTACT_NUM(String CONTACT_NUM) {
        this.CONTACT_NUM = CONTACT_NUM;
    }
}
