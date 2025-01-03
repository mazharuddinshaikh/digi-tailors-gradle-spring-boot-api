package in.tailor.digi.model.table;

import static in.tailor.digi.model.table.DtsColumn.*;

public enum DtsTable {
    DTS_SHOP("DTS_SHOP", new String[]{SHOP_ID, SHOP_NAME, SHOP_CODE, CREATED_AT, UPDATED_AT, ADDRESS_ID, HOLIDAY, OPEN_TIME, CLOSE_TIME, SHOP_STATUS, SHOP_IMAGE}),
    DTS_ADDRESS("DTS_ADDRESS",
            new String[]{ADDRESS_ID, ADDRESS_LINE_ONE, ADDRESS_LINE_TWO, CITY, STATE, ZIPCODE, CREATED_AT, UPDATED_AT,
                    UPDATED_BY}),
    DTS_USER("DTS_USER",
            new String[]{USER_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, MOBILE_NO, EMAIL, USER_NAME, PASSWORD,
                    ALTERNATE_MOBILE_NO, CREATED_AT, UPDATED_AT, ADDRESS_ID}),
    DTS_USER_SHOP_MAPPING("DTS_USER_SHOP_MAPPING",
            new String[]{USER_ID, SHOP_ID, USER_TYPE, CREATED_AT, UPDATED_AT}),
    DTS_CUSTOMER("DTS_CUSTOMER",
            new String[]{CUSTOMER_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, MOBILE_NO, EMAIL, CREATED_AT, UPDATED_AT, SHOP_ID, USER_ID}),
    DTS_DRESS_TYPE("DTS_DRESS_TYPE",
            new String[]{TYPE_ID, TYPE_NAME, TYPE_DESCRIPTION, COMMENT, CREATED_AT, UPDATED_AT, CREATED_BY}),
    DTS_USER_DRESS_TYPE("DTS_USER_DRESS_TYPE",
            new String[]{UDT_ID, DRESS_TYPE_ID, PRICE, CREATED_AT, UPDATED_AT}),
    DTS_DRESS("DTS_DRESS",
            new String[]{DRESS_ID, USER_ID, SHOP_ID, CUSTOMER_ID, COMMENT, CREATED_AT, UPDATED_AT});

    private final String tableName;
    private final String[] columns;

    DtsTable(String tableName, String[] columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getColumns() {
        return columns;
    }

}
