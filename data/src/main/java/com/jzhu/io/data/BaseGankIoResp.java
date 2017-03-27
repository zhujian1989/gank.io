package com.jzhu.io.data;

public class BaseGankIoResp<T> {

    private boolean error;

    private T results;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
