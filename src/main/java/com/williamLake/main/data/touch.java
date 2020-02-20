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
        this.length= bellLines.length - 1;
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

    @Override
    public String toString() {
        String returnString = "Touch{" + System.lineSeparator() +
                "callOrder = " + callOrder.toUpperCase()  + System.lineSeparator() +
                "isTrue = " + isTrue + System.lineSeparator() +
                "length = " + length + System.lineSeparator() +
                "bellLines =";
        int x = 0;
        boolean first = true;
        returnString = returnString + "  " + bellLines[0].toStringJustNum() + System.lineSeparator();
        for (int i = 0; i < length; i++){
            if(i != 0 && i % (this.getPlain_course_length()/(this.getNo_of_bells()-this.getHunt_bells())) == 0){

                returnString = returnString + "           " + callOrder.split("")[x].toUpperCase() + " " + bellLines[i].toStringJustNum() + System.lineSeparator();
                x++;
            }
        }
        returnString = returnString + "           " + callOrder.split("")[x].toUpperCase() + " " + bellLines[bellLines.length - 1].toStringJustNum() + System.lineSeparator();

        return returnString;
    }
}