package edu.murraystate.thesisproject.backendapi.core;

import edu.murraystate.thesisproject.backendapi.core.utils.types.SegmentMeta;

import java.util.ArrayList;
import java.util.List;

//TODO: Document
public class EvaluateDifference {
    final private Character[] charSet1;
    final private Character[] charSet2;

    //TODO: Document
    public EvaluateDifference(String str1, String str2) {
        this.charSet1 = (str1+"|").chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        this.charSet2 = (str2+"|").chars().mapToObj(c -> (char)c).toArray(Character[]::new);
    }

    //TODO: Document
    public SegmentMeta[] getDifferences() {
        int index1 = 0, index2 = 0;
        int diffStart1 = 0,   diffStart2 = 0;
        int diffSpan1 = 0, diffSpan2 = 0;
        List<SegmentMeta> diffs = new ArrayList<>();
        while ( index1 < charSet1.length && index2 < charSet2.length ) {
            if ( charSet1[index1] == charSet2[index2] ) {
                index1++;
                index2++;
                if ( diffSpan1 >= 1 || diffSpan2 >= 1 ) {
                    diffs.add( new SegmentMeta(diffStart1, diffStart1+diffSpan1, diffStart2, diffStart2+diffSpan2) );
                    diffSpan1 = 0;
                    diffSpan2 = 0;
                }
                diffStart1 = index1;
                diffStart2 = index2;
            } else if ( Character.isWhitespace(charSet1[index1]) ) {
                index1++;
                diffSpan1++;
            } else if ( Character.isWhitespace(charSet2[index2]) ) {
                index2++;
                diffSpan2++;
            } else {
                throw new IllegalArgumentException("Input strings can only vary in whitespace characters!");
            }
        }
        return diffs.toArray(new SegmentMeta[]{});
    }
}
