package bgpersonnel.budget.service.csv;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface CsvGenerator<T> {
    ByteArrayInputStream generateCSV(List<T> donnees,String[] header);
}
