package com.github.demo.aws.model;

public class StepFunctionObj {

    private String bucket;
    private String key;
    private StepResult validate;
    private StepResult process;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public StepResult getValidate() {
        return validate;
    }

    public void setValidate(StepResult validate) {
        this.validate = validate;
    }

    public StepResult getProcess() {
        return process;
    }

    public void setProcess(StepResult process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "StepFunctionObj{" +
                "bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", validate=" + validate +
                ", process=" + process +
                '}';
    }
}
