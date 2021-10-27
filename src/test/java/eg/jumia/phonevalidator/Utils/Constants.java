package eg.jumia.phonevalidator.Utils;

public class  Constants {
    public static final String PHONE_CONFIG_COUNTRY_CODE="237";
    public static final String PHONE_CONFIG_PATTERN="\\(237\\)\\ ?[2368]\\d{7,8}$";
    public static final String PHONE_CONFIG_COUNTRY_CODE_2="256";
    public static final String PHONE_CONFIG_PATTERN_2="\\(256\\)\\ ?\\d{9}$";
    public static final String PHONE_CONFIG_COUNTRY_CODE_NOT_EXISTS ="555";


    public static final String CUSTOMER_PHONE_WRONG_FORMAT="237 01114845271";
    public static final String CUSTOMER_PHONE_WRONG_SPECIAL_CHAR="(237*.//..) 01114845271";
    public static final String CUSTOMER_PHONE_WRONG_CODE="(556) 01114845271";
    public static final String CUSTOMER_PHONE_CORRECT="(237) 677046616";

    public static final String CUSTOMER_PHONE_INVALID="(256) 71466022155664";

    public static final String PHONE_CONFIG_COUNTRY_CODE_WRONG_PATTERN ="222";
    public static final String PHONE_CONFIG_PATTERN_WRONG_PATTERN="[";
    public static final String CUSTOMER_PHONE_WRONG_PATTERN="(222) 4545454511";
}
