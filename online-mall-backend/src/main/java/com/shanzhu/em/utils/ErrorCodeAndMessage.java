package com.shanzhu.em.utils;

/**
 * @Description:异常信息枚举
 * @author: zhangshuai
 * @date: 2024/09/02
 */
public enum ErrorCodeAndMessage {
    /**
     * 系统未知异常
     */
    SYSTEM_UNKNOW_ERROR(1, "系统未知异常"),

    MMP_DB_ERROR(2, "数据库异常"),

    MMP_REMOTE_UNEXCEPT_ERROR(3, "远程调用异常"),

    MMP_CLIENT_ERROR(4, "mmp客户端在应用方使用的异常"),

    /** ----------- mmp 客户端在应用方使用的异常枚举 ------------------- */

    MMP_CLIENT_SERVICE_RESULT_NULL(401, "远程调用mmp接口返回结果为null"),

    MMP_CLIENT_SERVICE_RESULT_NOT_SUCCESS(402, "远程调用mmp接口返回执行不成功"),

    ERROR_HSF_CONNECT_FAIL(403, "hsf connect is error"),

    /** ----------- 接口参数校验异常 ---------------------------- */

    MMP_CHECK_INPUT_ID(11, "传入的参数不是ID"),

    MMP_CHECK_INPUT_NULL(12, "传入的参数为空"),

    MMP_CHECK_INPUT_NICK_NULL(13, "传入的nick为空"),

    MMP_CHECK_NOT_SUB_NICK(14, "传入的nick不是子账号"),

    MMP_CHECK_NOT_IP_ADDRESS(15, "传入的ip不是合法的Ip地址"),

    MMP_CHECK_NOT_DATE(16, "传入的日期不是合法的日期格式"),

    MMP_CHECK_BATCH_ID(17, "汇金消息中用户ID或产品CODE为空"),

    MMP_CHECK_NOTIFY_MESSAGE(18, "从汇金收到的开通消息为空"),

    MMP_CHECK_NOT_USER_ID(19, "userId 非法"),

    MMP_CHECK_PERMISSION_CODES(20, "入参codes list 不能为空或者null"),

    MMP_CHECK_NOT_APPID(21, "传入的appId非法"),

    MMP_CHECK_NOT_DEPARTMENT_ID(21, "departmentId 非法"), MMP_CHECK_DEPARTMENTDO_ILLEGAL(22, "departmentDO 参数非法"),

    MMP_CHECK_PARAMS_ILLEGAL(99, "illegal params:"),

    /** ---------- 调用其它依赖系统时发生异常 三位数--------- */

    REMOTE_RESULT_NULL(102, "远程调用返回结果为空"),

    REMOTE_RESULT_MODULE_NULL(103, "远程调用返回结果内的模块为空"),

    REMOTE_RESULT_UNSUCCESS(104, "远程调用返回未成功"),

    USER_PROTECT_ERROR(110, "开启安全验证失败"),

    UIC_UPDATE_DATA(201, "更新UIC子账号操作异常"),

    UIC_EDITUSERTAG(202, "更新UIC用户标记位失败，请确认UIC的标记位是否有改动或删减"),

    UIC_USER_ISNOT_EXIST(203, "UIC不存在用户信息"),

    PUNISH_CONCEL_ERROR(401, "处罚中心撤销处罚异常"),

    WANGWANG_SYN_CHILD_DISPATCH(301, "同步旺旺分流标识操作异常"),

    WANGWANG_OPENEONLINE(302, "开启旺旺E客服操作异常"),

    WANGWANG_CLOSEEONLINE(303, "关闭旺旺E客服操作异常"),

    WANGWANG_NEED_LOGIN(306, "主账号没有登录旺旺，旺旺返回6"),

    WANGWANG_SYN_CHILD_EXPIRED(307, "同步旺旺到期时间至2030年1月1号时发生异常"),

    /** ---------- MMP业务异常 四位数--------- */
    MMP_EXCHANGE_UNOWED_SUB_ACCOUNT_IS_OWED(1101, "需要交换欠费状态的非欠费子账号是欠费状态"),

    MMP_EXCHANGE_OWED_SUB_ACCOUNT_IS_UNOWED(1102, "需要交换欠费状态的欠费子账号是非欠费状态"),

    MMP_SUB_ACCOUNT_SERVICE_ISNOT_OPEN(1103, "子账号服务未开启"),

    MMP_CHECK_MAIN_ACCOUNT(1104, "主账号对象为空"),

    MMP_CHECK_SUB_ACCOUNT(1105, "子账号对象为空"),

    MMP_CHECK_BATCH(1106, "批次对象为空"),

    MMP_CHECK_DATE(1107, "子账号套餐续费日期小于套餐当前的过期日期"),

    MMP_NOT_ORDER(1108, "买家用户没有订购过子账号服务"),

    MMP_CHECK_PARENT_DEPARTMENT_ORDER(1109, "父部门不存在"),

    MMP_CHECK_IS_EXIST_DEPARTMENT_ORDER(1110, "用该名称命名的部门已存在"),

    MMP_CHECK_IS_EXIST_SUB_DEPARTMENT_ORDER(1111, "当前部门不能删除,有子部门存在"),

    MMP_CHECK_IS_EXIST_DEPARTMENT_EMPLOYEE_ORDER(1112, "当前部门不能删除,有员工存在"),

    MMP_CHECK_IS_CHILD_ID_ORDER(1113, "不能把部门移到其下级部门下"),

    MMP_CHECK_IS_EXIST_DUTY_ORDER(1114, "该职务已经存在"),

    MMP_EMPLOYEE_SEX_IS_NOT_EXIST(1115, "性别元数据不存在"),

    MMP_EMPLOYEE_SUBACCOUNT_IS_NOT_EXIST(1116, "子账号不存在"),

    MMP_EMPLOYEE_SUBACCOUNT_HAS_ASSOCIATED(1117, "子账号已经被其他员工关联"),

    MMP_EMPLOYEE_DEPARTMENT_IS_NOT_EXIST(1118, "员工所属的部门不存在"),

    MMP_EMPLOYEE_DUTY_IS_NOT_EXIST(1119, "员工担任的职务不存在"),

    MMP_EMPLOYEE_NICKNAME_IS_EXIST(1120, "员工所用的花名已经存在"),

    MMP_EMPLOYEE_NUMBER_IS_EXIST(1121, "员工所有的工号已经存在"),

    MMP_EMPLOYEE_LEADER_IS_NOT_EXIST(1122, "员工的直接上级不存在"),

    MMP_EMPLOYEE_NAME_IS_NULL(1123, "员工姓名为空"),

    MMP_EMPLOYEE_IS_NOT_EXIST(1124, "员工对象不存在"),

    MMP_EMPLOYEE_RESIGNED_MODIFY_DENY(1125, "离职的员工不能修改更新"),

    /** ---------- 系统数据紊乱 负数--------- */

    MORE_THAN_ONE_PRESENT_BATCH(-11, "主账号的集市赠送批次记录多于一条"),

    MULTI_SHOP_PRESENT_BATCH(-12, "主账号的店铺种类赠送记录多于一种"),

    MULTI_SHOP_ORDER_BATCH(-13, "主账号购买记录多于一条"),

    OPEN_SUB_SERVICE_BEFORE_MIGRATION(-14, "在迁移线程之前用户打开子账号服务"),

    MORE_THAN_ONE_EMPLOYEE_ASSOCIATED_WITH_SUBACCOUNT(-15, "有一个以上员工与子账号相关"),

    /** ---------- tddl 异常--------- */
    EXEC_GET_SEQUENCE(-111, "获取tlld Sequence异常"),

    EXEC_FIND_NO_SEQUENCE(-112, "未找到对应表的tlld Sequence"),

    LOGIC(-9999, "逻辑漏洞"),

    PRODUCT_ID_IS_NULL(100005, "id为空");

    private ErrorCodeAndMessage(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public String getStringErrorCode() {
        return String.valueOf(errorCode);
    }


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorCode + ":" + errorMessage;
    }
}
