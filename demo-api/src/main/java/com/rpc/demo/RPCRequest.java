package com.rpc.demo;

import java.io.Serializable;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 10:36
 */
public class RPCRequest implements Serializable {

    public static final Serializable Serializable=1L;
    private String className;
    private String methodName;
    private Object [] pararms;

    public RPCRequest(){

    }
    public RPCRequest(String className, String methodName, Object[] pararms) {
        this.className = className;
        this.methodName = methodName;
        this.pararms = pararms;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getPararms() {
        return pararms;
    }

    public void setPararms(Object[] pararms) {
        this.pararms = pararms;
    }
}
