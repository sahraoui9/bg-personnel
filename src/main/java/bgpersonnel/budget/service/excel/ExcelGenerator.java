package bgpersonnel.budget.service.excel;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelGenerator<T> {
    ByteArrayInputStream generateExcel(List<T> data, String[] headers);
}
