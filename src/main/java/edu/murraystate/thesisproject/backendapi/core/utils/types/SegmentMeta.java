package edu.murraystate.thesisproject.backendapi.core.utils.types;

import java.util.Arrays;


//TODO: Document
public class SegmentMeta {
    final private int startIndex1;
    final private int endIndex1;
    final private int startIndex2;
    final private int endIndex2;

    //TODO: Document
    public SegmentMeta(int startIndex1, int endIndex1, int startIndex2, int endIndex2){
        this.startIndex1 = startIndex1;
        this.endIndex1 = endIndex1;
        this.startIndex2 = startIndex2;
        this.endIndex2 = endIndex2;
    }

    public int[] getSegmentData(){
        return new int[]{startIndex1, endIndex1, startIndex2, endIndex2};
    }

    //TODO: Document
    @Override
    public boolean equals(Object other){
        if(!(other instanceof SegmentMeta)){
            return false;
        } else {
            return (Arrays.equals(this.getSegmentData(), ((SegmentMeta) other).getSegmentData()));
        }
    }

    //TODO: Document
    @Override
    public String toString(){
        return "Seg: (" + startIndex1 + ", " + endIndex1 + ") & (" + startIndex2 + ", " + endIndex2 + ")";
    }
}
