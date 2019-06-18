package com.cccts.gateway808;

import com.cccts.helpers.Log4jHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerLog {
//    static {
//        Log4jHelper.useCustomerConfig();
//        DATA_LOG  = LogManager.getLogger("DataLog");
//        DEBUG_LOG = LogManager.getLogger("DebugLog");
//    }

    /**
     * 记录所有通讯数据，包含收发数据
     */
    public static Logger DATA_LOG = LogManager.getLogger("DataLog");;

    public static Logger DEBUG_LOG = LogManager.getLogger("DebugLog");
}
