package edu.murraystate.thesisproject.backendapi.records;

import edu.murraystate.thesisproject.backendapi.core.utils.types.SegmentLiteral;
import edu.murraystate.thesisproject.backendapi.core.utils.types.SegmentMeta;

public record BulkEvalData(long id, String fileTitle, int styleErrors, long ETCR, int CommentCount, SegmentMeta[] issueSegments, SegmentLiteral[] issueSegmentLiterals){ }
