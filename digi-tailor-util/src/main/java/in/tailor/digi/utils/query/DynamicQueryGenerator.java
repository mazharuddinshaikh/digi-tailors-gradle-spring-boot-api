package in.tailor.digi.utils.query;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;


public class DynamicQueryGenerator {
	public static final String NUMBER = "NUMBER";
	private static final String SELECT = "SELECT ";
	private static final String DOT = ".";
	private static final String AS = " AS ";
	private static final String BLANK_SPACE = " ";
	private static final String EMPTY = "";
	private static final String FROM = " FROM ";
	private static final String WHERE = " WHERE ";
	private static final String EQUALS = " = ";
	private static final String IN = " IN ";
	private static final String SINGLE_QUOTE = "'";
	private static final String AND = " AND ";
	private static final String IS_NULL = " IS NULL ";
	private static final String STRING = "STRING";
	private static final String OFFSET = " OFFSET ";
	private static final String LIMIT = " LIMIT ";

	private DynamicQueryGenerator() {
	}

	public static String generateSelectQuery(DtsSqlQueryParameter dtsSqlQueryParameter) {
		String query = getSelectClause(dtsSqlQueryParameter.getColumnMap()); // select clause
		query += getFromClause(dtsSqlQueryParameter); // from clause
		if (dtsSqlQueryParameter.getIncludeFilters() != null && !dtsSqlQueryParameter.getIncludeFilters().isEmpty()) {
			query += getWhereClause(dtsSqlQueryParameter); // where clause
		}
		if (dtsSqlQueryParameter.getLimit() > 0) {
			query += LIMIT + dtsSqlQueryParameter.getLimit() + OFFSET + dtsSqlQueryParameter.getOffset();
		}
		return query;
	}

	private static String getSelectClause(Map<String, List<String>> columnList) {
		StringBuilder selectClauseBuilder = new StringBuilder(SELECT);
		for (Entry<String, List<String>> entry : columnList.entrySet()) {
			selectClauseBuilder
					.append(entry.getValue().stream().map(column -> entry.getKey() + DOT + column + AS + column)
							.collect(Collectors.joining(", ", EMPTY, BLANK_SPACE)));
		}
		return selectClauseBuilder.toString();
	}

	private static String getFromClause(DtsSqlQueryParameter dtsSqlQueryParameter) {
		StringBuilder fromClauseBuilder = new StringBuilder(FROM);
		if (dtsSqlQueryParameter.getColumnMap().size() == 1) {
			String tableName = dtsSqlQueryParameter.getColumnMap().keySet().iterator().next();
			return fromClauseBuilder.append(tableName + BLANK_SPACE + tableName).toString();
		}
//		add join condition
//		if (dtsSqlQueryParameter.getInnerJoinMap() != null && !dtsSqlQueryParameter.getInnerJoinMap().isEmpty()) {
//			for(Map.Entry<String, List<String>> entry: dtsSqlQueryParameter.getInnerJoinMap().entrySet()) {
//				entry.getKey(); //column name
//				for(String tableNameOuter: entry.getValue()) {
//					for(String tableNameInner: entry.getValue()) {
//						if(!tableNameInner.equals(tableNameOuter)) {
//							tableNameOuter + DOT + entry.getKey() + EQUALS + tableNameInner + DOT + entry.getKey();  
//						}
//					}
//				}
//			}
// 		}
		return null;
	}

	private static String getWhereClause(DtsSqlQueryParameter dtsSqlQueryParameter) {
		StringJoiner joiner = new StringJoiner(AND);
		for (String tableName : dtsSqlQueryParameter.getIncludeFilters().keySet()) {
			for (Entry<String, List<String>> entry : dtsSqlQueryParameter.getIncludeFilters().get(tableName)
					.entrySet()) {
				if (Objects.isNull(entry.getValue()) || entry.getValue().isEmpty()) {
					joiner.add(tableName + DOT + entry.getKey() + IS_NULL);
				} else {
					joiner.add(generateWhereConditionValues(dtsSqlQueryParameter.getColumnDataType(), tableName,
							entry.getKey(), entry.getValue()));
				}

			}
		}
		return WHERE + joiner.toString();
	}

	private static String generateWhereConditionValues(Map<String, Map<String, String>> columnDataType,
			String tableName, String columnName, List<String> values) {
		String dataType = getDataType(columnDataType, tableName, columnName);
		if (values.size() == 1) {
			return tableName + DOT + columnName + EQUALS + getPrefix(dataType, true) + values.get(0)
					+ getSuffix(dataType, true);
		} else {
			return tableName + DOT + columnName + IN + values.stream().map(val -> val).collect(
					Collectors.joining(getDelimeter(dataType), getPrefix(dataType, false), getSuffix(dataType, false)));
		}
	}

	private static String getDelimeter(String dataType) {
		if (NUMBER.equalsIgnoreCase(dataType)) {
			return ", ";
		}
		return "', '";
	}

	private static String getPrefix(String dataType, boolean isSingleValue) {

		if (NUMBER.equalsIgnoreCase(dataType)) {
			return isSingleValue ? EMPTY : "(";
		}
		return isSingleValue ? SINGLE_QUOTE : "('";
	}

	private static String getSuffix(String dataType, boolean isSingleValue) {
		if (NUMBER.equalsIgnoreCase(dataType)) {
			return isSingleValue ? EMPTY : ")";
		}
		return isSingleValue ? SINGLE_QUOTE : "')";
	}

	private static String getDataType(Map<String, Map<String, String>> columnDataType, String tableName,
			String columnName) {
		if (columnDataType == null || columnDataType.get(tableName) == null
				|| columnDataType.get(tableName).get(columnName) == null) {
			return STRING;
		}
		return columnDataType.get(tableName).get(columnName);
	}
}
