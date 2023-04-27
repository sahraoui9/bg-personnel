package bgpersonnel.budget.service.pdf;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PdfGenerator<T> {
    ByteArrayInputStream generatePdf(List<T> data, String templateHtml,String[] header);
}
