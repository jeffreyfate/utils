package com.jeffthefate.utils.json.parse;

public class Play {

    private boolean opener;
    private boolean setCloser;
    private boolean encoreCloser;
    private Pointer show;
    private int slot;
    private Pointer song;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public boolean isOpener() {
        return opener;
    }

    public void setOpener(boolean opener) {
        this.opener = opener;
    }

    public boolean isSetCloser() {
        return setCloser;
    }

    public void setSetCloser(boolean setCloser) {
        this.setCloser = setCloser;
    }

    public boolean isEncoreCloser() {
        return encoreCloser;
    }

    public void setEncoreCloser(boolean encoreCloser) {
        this.encoreCloser = encoreCloser;
    }

    public Pointer getShow() {
        return show;
    }

    public void setShow(Pointer show) {
        this.show = show;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Pointer getSong() {
        return song;
    }

    public void setSong(Pointer song) {
        this.song = song;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "opener: " + isOpener() + ", setCloser: " + isSetCloser() +
                ", encoreCloser: " + isEncoreCloser() + ", show: " + getShow() +
                ", slot: " + getSlot() + ", song: " + getSong() +
                ", createdAt: " + getCreatedAt() + ", updatedAt: " +
                getUpdatedAt() + ", objectId: " + getObjectId();
    }
}
