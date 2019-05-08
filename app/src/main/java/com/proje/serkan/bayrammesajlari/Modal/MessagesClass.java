package com.proje.serkan.bayrammesajlari.Modal;

/**
 * Created by gökhan on 20.07.2017.
 */

public class MessagesClass {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesClass that = (MessagesClass) o;

        return mID == that.mID;

    }

    @Override
    public int hashCode() {
        return mID;
    }

    int mID;
    String icerik;

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }


}
