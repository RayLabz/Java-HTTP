package com.raylabz.javahttp;

public interface OnSuccessListener<T extends HTTPResponse<?>> {

    void onSuccess(T response) throws Exception;

}
