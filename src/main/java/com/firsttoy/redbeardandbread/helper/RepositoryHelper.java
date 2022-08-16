package com.firsttoy.redbeardandbread.helper;

public interface RepositoryHelper<T> {

    T findById(Long id);
}
