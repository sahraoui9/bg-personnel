package bgpersonnel.budget.reporting;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class DataReport {
    private String type;
    private String date;
}
