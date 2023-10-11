package edu.murraystate.thesisproject.backendapi.records;

import edu.murraystate.thesisproject.backendapi.core.utils.types.SegmentMeta;

public record BulkEvalData(long id, String fileTitle, SegmentMeta[] issueSegments, int styleErrors, long ETCR, int CommentCount){ }
