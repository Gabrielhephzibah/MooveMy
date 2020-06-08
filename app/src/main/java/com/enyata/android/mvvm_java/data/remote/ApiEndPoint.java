package com.enyata.android.mvvm_java.data.remote;

import com.enyata.android.mvvm_java.BuildConfig;

public class ApiEndPoint {
    public static final String LOGIN_INSPECTOR = BuildConfig.BASE_URL + "/user/signin";

    public static final String INTAKE_CREATE_REPORT = BuildConfig.BASE_URL + "/vehicle/reports";

    public  static  final String INSPECTOR_LIST = BuildConfig.BASE_URL + "/vehiclereports/reports/inspector";

    public  static  final  String  INSPECTOR_DETAILS_REPORT = BuildConfig.BASE_URL + "/vehiclereports/reports/inspector";

    public  static  final  String VIN_API = BuildConfig.VIN_API + "/decode?vin=";

    public static final String REG_NUMBER_CHECK = BuildConfig.BASE_URL + "/vehicle/check/monthly";

    public static final  String INTAKE_RULE_CHECK = BuildConfig.BASE_URL + "/vehicle/check/intake";

    public static final  String MONTHLY_VEHICLE_LIST= BuildConfig.BASE_URL + "/vehiclereports/intake";

    public static final  String MAINTENANCE_SCHEDULE= BuildConfig.BASE_URL + "/vehiclereports/maintenance/schedule";

    public static final  String CHECK_INTAKE_REPORT= BuildConfig.BASE_URL + "/vehiclereports/intake/check";

    public static final  String CHECK_MONTHLY_REPORT= BuildConfig.BASE_URL + "/vehiclereports/monthly";

    public static final  String CHECK_MAINTENANCE_REPORT= BuildConfig.BASE_URL + "/vehiclereports/maintenance";

    public static final  String CHECK_REPAIRS_REPORT= BuildConfig.BASE_URL + "/vehiclereports/repairs";

    public static final  String GET_ACCEPTANCE_RESULT= BuildConfig.BASE_URL + "/vehiclereports/acceptance/value";

    public static final  String GET_ALL_VEHICLE_IN_DATABASE= BuildConfig.BASE_URL + "/vehicle?limit=";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
