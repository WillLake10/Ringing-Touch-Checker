package com.williamlake.main.data;

public class Touch extends Method {
    private String callOrder;
    private Boolean isTrue;
    private String[][] bellLines;

    public Touch(String callOrder, Boolean isTrue, String[][] bellLines, Method method) {
        super(method.getMethod_proper(), method.getNotation(), method.getNo_of_bells(), method.getBob_notation(), method.getSingle_notation(), method.getCall_point(), method.getHunt_bells());
        this.callOrder = callOrder;
        this.isTrue = isTrue;
        this.bellLines = bellLines;
    }

    public String getCallOrder() {
        return callOrder;
    }

    public void setCallOrder(String callOrder) {
        this.callOrder = callOrder;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public String[][] getBellLines() {
        return bellLines;
    }

    public void setBellLines(String[][] bellLines) {
        this.bellLines = bellLines;
    }
}