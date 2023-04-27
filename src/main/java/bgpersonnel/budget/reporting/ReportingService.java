package bgpersonnel.budget.reporting;

import java.io.ByteArrayInputStream;

public interface ReportingService {
    ByteArrayInputStream generateReport(ReportRequest reportRequest);

}
