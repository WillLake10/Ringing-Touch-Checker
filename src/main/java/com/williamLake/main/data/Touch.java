package com.williamLake.main.data;

import com.williamLake.main.touchGenaration;

public class Touch extends Method {
    private String callOrder;
    private String calledFromBell;
    private Boolean isTrue;
    private Line[] bellLines;
    private int length;

    public Touch(String callOrder, Method method, int type) {
        super(method.getMethod_proper(), method.getNotation(), method.getNo_of_bells(), method.getBob_notation(), method.getSingle_notation(), method.getCall_point(), method.getHunt_bells());
        if(type == 1){
            this.callOrder = callOrder;
        }else if(type == 2){
            this.calledFromBell = callOrder;
        }

        this.bellLines = touchGenaration.getTouchBellLine(callOrder, method);
        this.isTrue = touchGenaration.checkLineIsTrue(bellLines);
        this.length= bellLines.length - 1;
        if(type == 1){
            this.calledFromBell = touchGenaration.getCalledFromBell(callOrder, bellLines, method.getLead_length(), method.getNo_of_bells());
        }else if(type == 2){

        }

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
                "   callOrder = " + callOrder.toUpperCase()  + System.lineSeparator() +
                "   calledFromBell = " + calledFromBell + System.lineSeparator() +
                "   isTrue = " + isTrue + System.lineSeparator() +
                "   length = " + length + System.lineSeparator() +
                "   bellLines =";
        int x = 0;
        boolean first = true;
        returnString = returnString + "  " + bellLines[0].toStringJustNum() + System.lineSeparator();
        for (int i = 0; i < length; i++){
            if(i != 0 && i % (this.getPlain_course_length()/(this.getNo_of_bells()-this.getHunt_bells())) == 0){

                returnString = returnString + "              " + callOrder.split("")[x].toUpperCase() + " " + bellLines[i].toStringJustNum() + System.lineSeparator();
                x++;
            }
        }
        returnString = returnString + "              " + callOrder.split("")[x].toUpperCase() + " " + bellLines[bellLines.length - 1].toStringJustNum() + System.lineSeparator();
        returnString = returnString + "}";
        return returnString;
    }
}