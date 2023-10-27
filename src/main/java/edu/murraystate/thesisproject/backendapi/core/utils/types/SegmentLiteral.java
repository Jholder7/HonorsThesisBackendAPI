package edu.murraystate.thesisproject.backendapi.core.utils.types;

import java.io.Serializable;
import java.util.Arrays;

public class SegmentLiteral {
    final private String preStr;
    final private String postStr;
    final private String deductionType;

    //TODO: Document
    public SegmentLiteral(String preString, String postString, String deductionAmount){
        this.preStr = preString;
        this.postStr = postString;
        this.deductionType = deductionAmount;
    }

    public String[] getSegmentLiteralData() {
        return new String[]{preStr, postStr, deductionType};
    }

    //TODO: Document
    @Override
    public String toString(){
        return "SegLit: (preStr: " + preStr + ", postStr: " + postStr + ")";
    }
}
