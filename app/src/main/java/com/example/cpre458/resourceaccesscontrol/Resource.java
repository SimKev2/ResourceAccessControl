package com.example.cpre458.resourceaccesscontrol;

/**
 * Class for representing a single Resource.
 */

public class Resource {
    private boolean lock;
    public Task owner;

    public int priority;

    public Resource() {
        this.lock = false;
        this.priority = -1;
        this.owner = null;
    }

    public boolean canLock() {
        return !this.lock;
    }

    public boolean lock(Task t) {
        if (!this.lock) {
            this.lock = true;
            this.owner = t;
            return true;
        }
        return false;
    }

    public boolean unlock() {
        if (this.lock) {
            this.lock = false;
            this.owner = null;
            return true;
        }
        return false;
    }
}
