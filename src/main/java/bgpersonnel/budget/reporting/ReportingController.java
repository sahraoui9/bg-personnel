package bgpersonnel.budget.reporting;


import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;

    }

    @GetMapping()
    public ResponseEntity<Resource> generateReport(@RequestBody ReportRequest reportRequest) {
        InputStreamResource file = new InputStreamResource(reportingService.generateReport(reportRequest));
        String filename = null;
        String contentType = null;

        switch (reportRequest.getReportType()) {
            case CSV -> {
                filename = "tutorials.csv";
                contentType = "text/csv";
            }
            case XLS -> {
                filename = "tutorials.xlsx";
                contentType = "application/vnd.ms-excel";
            }
            case PDF -> {
                filename = "tutorials.pdf";
                contentType = "application/pdf";
            }
            default -> throw new IllegalStateException("Unexpected value: " + reportRequest.getReportType());
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType(contentType))
                .body(file);
    }

}
