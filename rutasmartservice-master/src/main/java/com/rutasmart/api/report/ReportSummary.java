package com.rutasmart.api.report;

import java.util.List;

public record ReportSummary(
        int trips,
        long incidents,
        int punctuality,
        List<ActivityItem> activity,
        List<ReportRow> rows
) {
}
