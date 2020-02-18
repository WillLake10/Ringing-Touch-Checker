package com.williamlake.main.data;

import static com.williamlake.main.touchGenaration.checkLineIsTrue;
import static com.williamlake.main.touchGenaration.getTouchBellLine;

public class Touch extends Method {
    private String callOrder;
    private Boolean isTrue;
    private Line[] bellLines;
    private int length;

    public Touch(String callOrder, Method method) {
        super(method.getMethod_proper(), method.getNotation(), method.getNo_of_bells(), method.getBob_notation(), method.getSingle_notation(), method.getCall_point(), method.getHunt_bells());
        this.callOrder = callOrder;
        this.bellLines = getTouchBellLine(callOrder, method);
        this.isTrue = checkLineIsTrue(bellLines);
        this.length= bellLines.length;
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

    public Line[] getBellLines() {
        return bellLines;
    }

    public void setBellLines(Line[] bellLines) {
        this.bellLines = bellLines;
    }
}