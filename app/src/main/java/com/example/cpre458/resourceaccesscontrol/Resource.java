package com.example.cpre458.resourceaccesscontrol;

/**
 * Class for representing a single Resource.
 */

public class Resource {
    private boolean lock;

    public Resource() {
        this.lock = false;
    }

    public boolean canLock() {
        return !this.lock;
    }

    public boolean lock() {
        if (!this.lock) {
            this.lock = true;
            return true;
        }
        return false;
    }

    public boolean unlock() {
        if (this.lock) {
            this.lock = false;
            return true;
        }
        return false;
    }
}
