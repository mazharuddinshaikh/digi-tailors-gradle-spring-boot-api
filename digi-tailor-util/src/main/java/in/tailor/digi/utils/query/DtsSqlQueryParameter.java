package in.tailor.digi.utils.query;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DtsSqlQueryParameter {
	private Map<String, List<String>> columnMap;
	private Map<String, Map<String, String>> columnDataType;
	private Map<String, Map<String, List<String>>> includeFilters;
	private Map<String, List<String>> innerJoinMap;
	private int limit;
	private int offset;
}
