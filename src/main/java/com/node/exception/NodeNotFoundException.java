package com.node.exception;

public class NodeNotFoundException extends RuntimeException {

    static final long serialVersionUID = -687991492884005033L;

    public NodeNotFoundException(String message) {
        super(message);
    }

}
