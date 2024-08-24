package com.shanzhu.em.service.util;

/**
 * 异常类
 *
 * @author zhangshuai
 * @Date 2024/08/24
 */
public enum ErrorCodeEnum {
    /**
     * 订单明细中交付信息的日期早于当前系统时间
     */
    ORDER_ENTRY_DELIVERY_DATE_EARLIER_THAN_NOW("order_entry_delivery_date_earlier_than_now"),
    /**
     * 校验结果空
     */
    VERIFY_RESULT_IS_NULL("verify_result_is_null"),
    /**
     *
     */
    PO_CONFIGURATION_ALREADY_EXIST("po_configuration_already_exist"),
    /**
     *
     */
    FORBIDDEN("biz_forbidden"),
    /**
     *
     */
    UNKNOWN("unknown"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR("system_error"),
    /**
     * 无权限
     */
    NO_PERMISSION("no_permission"),
    /**
     * 参数无效
     */
    INVALID_PARAM("invalid_param"),
    /**
     * 校验未通过
     */
    NO_PASS_VALIDATE("no_pass_validate"),
    /**
     * 校验未通过
     */
    DELIVERY_SCHEDULE_STATUS_ERROR("delivery_schedule_status_error"),
    /**
     * 参数无效
     */
    INVALID_PEROID_TRADE_RULE("invalid_peroid_trade_rule"),
    /**
     * 操作数据中包含退款中数据
     */
    ENTRIES_CONTAIN_REFUNDING("entries_contains_refunding"),
    /**
     * 订单退款中
     */
    ORDER_REFUNDING("order_refunding"),
    /**
     * 参数为空
     */
    PARAM_IS_NULL("param_is_null"),
    /**
     * 公司参数为空
     */
    PARAM_COMPANY_IS_NULL("param_company_is_null"),
    /**
     * 非法用户，用户不存在，或者其他用户错误
     */
    INVALID_USER("invalid_user"),
    /**
     * 创建结算时，需结算的采购订单记录数量不匹配
     */
    INVALID_ORDER_RESULT("invalid_order_result"),
    /**
     * 非直营通货订单
     */
    NOT_DIRECTMALL_ORDER("not_directmall_order"),
    /**
     * 创建结算单失败
     */
    CREATE_SETTLE_FAILED("create_settle_failed"),
    /**
     * 未查询到数据
     */
    ENTITY_MISSING("entity_missing"),
    /**
     * 插入数据失败
     */
    INSERT_FAILED("insert_failed"),
    /**
     * 更新数据失败
     */
    UPDATE_FAILED("update_failed"),
    /**
     * 核销规则为空
     */
    MATCHER_LIST_IS_NULL("matcher_List_is_null"),
    /**
     * 交易账期可用授信额度不足
     */
    QUOTA_NOT_ENOUGH("quota_not_enough"),
    /**
     * 订单不是担保交易
     */
    ORDER_NOT_SECURED_TRADE("order_not_secured_trade"),
    /**
     * 到账金额匹配不到任何订单
     */
    RECEIVE_MONEY_AMOUNT_NOT_MATCH_ANY_ORDER("receive_money_amount_not_match_any_order"),
    /**
     * 保存资金明细失败
     */
    SAVE_FUND_DETAIL_FAILED("save_fund_detail_failed"),
    /**
     * 订单还未付款
     */
    ORDER_WAIT_PAY("order_wait_pay"),
    /**
     * 订单已经全部到账
     */
    ORDER_RECEIVE_MONEY_COMPLETE("order_receive_money_complete"),
    /**
     * 明细行已经标记了收货完成，不能继续确认收货
     */
    COMPLETED_DELIVERY("completed_delivery"),
    /**
     * 明细数量过多
     */
    TOO_MUCH_GOODS("too_much_goods"),
    /**
     * 已经存在发票号
     */
    EXIST_CONFIRM_CODE("exist_confirm_code"),
    /**
     * 幂等校验失败
     */
    IDEMPOTENT_CHECK_FAILED("idempotent_check_failed"),
    /**
     * 查询不到关系列表
     */
    RELATION_LIST_IS_NULL("relation_List_is_null"),
    /**
     * 查询订单失败
     */
    QUERY_ORDER_ERROR("query_order_error"),
    /**
     * 查询订单结果为空
     */
    ORDER_IS_NULL("order_is_null"),
    /**
     * 订单已经发货
     */
    ALREADY_SEND_GOODS("already_send_goods"),
    /**
     * 采购订单取消失败
     */
    CANCEL_FAILED("cancel_failed"),
    /**
     * 操作失败
     */
    OPERATION_FAILED("operation_failed"),
    /**
     * 操作前置状态不对
     */
    PRE_STATUS_WRONG("pre_status_wrong"),
    /**
     * 冲销-收货单状态错误
     */
    WO_STATUS_WRONG_RECEIVE("wo_status_wrong_receive"),
    /**
     * 冲销-收货单状态错误
     */
    WO_STATUS_WRONG_TRADE("wo_status_wrong_trade"),
    /**
     * 冲销-退货单状态非账期
     */
    WO_STATUS_WRONG_RETURN("wo_status_wrong_return"),
    /**
     * 冲销-结算单状态错误
     */
    WO_STATUS_WRONG_SETTLE("wo_status_wrong_settle"),
    /**
     * 冲销-结算单状态错误
     */
    WO_STATUS_WRONG_ORDER("wo_status_wrong_order"),
    /**
     * 交易方式为非账期的才可开票
     */
    CONFIRM_INVOICE_STATUS_WRONG("confirm_invoice_status_wrong"),
    /**
     * 退回条件不满足(未发货,未结算,未付款的订单才可以退回)
     */
    RETURN_ORDER_WRONG("return_order_wrong"),
    /**
     * 系统处理繁忙，请稍后再试(加锁失败)
     */
    SYSTEM_TRY_LOCK_FAILED("system_try_lock_failed"),
    /**
     * 确认收货进行中
     */
    CONFIRMGOODS_IS_RUNNING("confirmgoods_is_running"),

    /**
     * 会话已经过期
     */
    SESSION_EXPIRED("session_expired"),

    /**
     * 物料列表长度超过限制
     */
    MATERIAL_LIST_MAX_SIZE_ERROR("material_list_max_size_error"),


    /**
     * 数量异常
     */
    AMOUNT_ERROR("amount_error"),

    /**
     * 单价异常
     */
    PEA_ERROR("pea_error"),

    /**
     * 物料失效
     */
    MATERIAL_INVALID("material_invalid"),

    /**
     * 赠品失效
     */
    PRESENT_INVALID("present_invalid"),

    /**
     * 交易类型不能为空
     */
    TRADE_TYPE_NOT_NULL("trade_type_not_null"),

    /**
     * 支付类型
     */
    PAYMENT_TYPE_LIST_NOT_NUll("payment_type_list_not_null"),

    /**
     * 支付类型不能为空
     */
    PAYMENT_TYPE_IS_NUll("payment_type_is_null"),

    /**
     * 收货地址不能为空
     */
    RECEIVE_ADDRESS_NOT_NULL("receive_address_not_null"),

    /**
     * 公司名称和编码不能为空
     */
    COMPANY_NAME_OR_CODE_NOT_NULL("company_name_or_code_not_null"),


    /**
     * 采购组名称和编码不能为空
     */
    PURCHASING_GROUP_NAME_OR_CODE_NOT_NOLL("purchasing_group_name_or_code_not_null"),


    /**
     * 物料列表不能为空
     */
    MATERIAL_LIST_NOT_NULL("material_list_not_null"),


    /**
     * 工厂名称和编码不能为空
     */
    FACTORY_NAME_OR_CODE_NOT_NULL("factory_name_or_code_not_null"),

    /**
     * 总价不能为空
     */
    TOTAL_COST_NOT_NULL("total_cost_not_null"),


    /**
     * 物料总价（未税）不能为空
     */
    MATERIAL_TPEA_NOT_NULL("material_tpea_not_null"),

    /**
     * 税率不能为空
     */
    TAX_RATE_NOT_NULL("tax_rate_not_null"),

    /**
     * 数量不能为空
     */
    AMOUNT_NOT_NULL("amount_not_null"),

    /**
     * 数量小数位超过限制
     */
    AMOUNT_DECIMAL_PLACES_MORE_THAN_LIMIT("amount_decimal_places_more_than_limit"),

    /**
     * 单价不能为空
     */
    PEA_NOT_NULL("pea_not_null"),

    /**
     * 收货日期不能为空
     */
    DELIVERY_DATE_NOT_NULL("delivery_date_not_null"),

    /**
     * 当前状态不允许编辑
     */
    NOT_ALLOW_TO_EDIT("not_allow_to_edit"),

    /**
     * 不允许同意
     */
    NOT_ALLOW_TO_AGREE("not_allow_to_agree"),

    /**
     * 物料单价超过最大限制
     */
    MATERIAL_PEA_MORE_THAN_MAX_LENGTH("material_pea_more_than_max_length"),

    /**
     * 物料总价超过最大限制
     */
    MATERIAL_TPEA_MORE_THAN_MAX_LENGTH("material_tpea_more_than_max_length"),


    /**
     * 订单总价超过最大限制
     */
    ORDER_MATERIAL_TPEA_MORE_THAN_MAX_LENGTH("order_material_tpea_more_than_max_length"),


    /**
     * 物料失效头信息
     */
    MATERIAL_INVALID_HEADER_MSG("material_invalid_header_msg"),
    /**
     * 重复下单
     */
    SUBMIT_ORDER_REPEAT("submit_order_repeat"),

    /**
     * 物料编码为空
     */
    MATERIAL_CODE_IS_NULL("material_code_is_null"),

    /**
     * 配置项至少勾选一个功能 交期
     */
    PO_CONFIGURATION_STATUS_VALUE_IS_NULL("po_configuration_status_value_is_null"),

    /**
     * 选择的物料编码为空  交期
     */
    CHOOSE_MATERIAL_CODE_IS_NULL("choose_material_code_is_null"),

    /**
     * 交货计划最多批量操作量限制
     */
    DELIVERY_BATCH_MAX_SIZE_ERROR("delivery_batch_max_size_error"),

    /**
     * 退回交货计划退回原因超长
     */
    DELIVERY_REJECT_MAX_SIZE_ERROR("delivery_reject_max_size_error"),
    /**
     * 供应商编码不能为空
     */
    ORDER_SUPPLIER_CODE_IS_NULL("order_supplier_code_is_null"),

    /**
     * 发货子单状态不对不允许收货
     */
    NOT_ALLOW_RECEIVE_BY_SEND_GOODS_NOTE("not_allow_to_receive_by_send_goods_note"),

    /**
     * 创建数量超过上限
     */
    BATCH_CREATE_MAX_SIZE_ERROR("batch_create_max_size_error"),
    /**
     * 获取退货单失败
     */
    RETURNGOODS_SYNC_SETTLE_FAILED("returngoods_sync_settle_failed"),
    /**
     * 获取退货单失败
     */
    RETURNGOODS_INVALID("RETURNGOODS_INVALID"),
    /**
     * 获取退货单项失败
     */
    RETURNGOODS_ENTRYS_INVALID("RETURNGOODS_ENTRYS_INVALID"),
    /**
     * 退货单查询采购订单失败
     */
    RETURNGOODS_QUERY_PO_FAILED("returngoods_query_po_failed"),
    /**
     * 退货单查询采购子订单失败
     */
    RETURNGOODS_QUERY_PO_ENTRYS_FAILED("returngoods_query_po_entrys_failed"),
    /**
     * 创建退货单失败
     */
    RETURNGOODS_CREATE_FAILED("create_returngoods_failed"),
    /**
     * 分配退货量失败
     */
    RETURNGOODS_DISTRIBUTE_QUANITITY_FAILED("returngoods_distribute_quantity_failed"),
    /**
     * 退货单DB回滚
     */
    RETURNGOODS_DB_ROLLBACK("returngoods_db_rollback"),
    /**
     * 退回退货单失败
     */
    RETURNGOODS_REJECT_FAILED("reject_returngoods_failed"),
    /**
     * 更新退货单失败
     */
    RETURNGOODS_UPDATE_FAILED("update_returngoods_failed"),
    /**
     * 关闭退货单失败
     */
    RETURNGOODS_CLOSE_FAILED("close_returngoods_failed"),
    /**
     * 确认退货单失败
     */
    RETURNGOODS_CONFIRM_FAILED("confirm_returngoods_failed"),
    /**
     * 退货单同步交易失败
     */
    RETURNGOODS_TREADE_CREATE_FAILED("returngoods_trade_create_failed"),
    /**
     * 退货单对采购子订单加锁失败
     */
    RETURNGOODS_LOCK_PO_FAILED("lock_po_returngoods_failed"),
    /**
     * 退货单对收货单加锁失败
     */
    RETURNGOODS_LOCK_RECEIVE_FAILED("lock_receive_returngoods_failed"),
    /**
     * 获取收退货关系失败
     */
    RETURN_RECEIVE_RELATION_FAILED("return_receive_relation_failed"),
    /**
     * 收货日期不能为空
     */
    RECEIVE_DATE_NOT_NULL("receive_date_not_null"),
    /**
     * 收货数量不能为空
     */
    RECEIVE_QUANTITY_NOT_NULL("receive_quantity_not_null"),
    /**
     * 数量小数位数超长
     */
    QUANTITY_DECIMAL_NUM_EXCEED("quantity_max_size_error"),
    /**
     * 仓库不能为空
     */
    STOREHOUSE_NOT_NULL("storehouse_not_null"),
    /**
     * 内部附言不能太长
     */
    BUYERINSIDEREMARK_MAX_SIZE_ERROR("buyerinsideremark_max_size_error"),
    /**
     * 供应商附言不能太长
     */
    BUYEROUTSIDEREMARK_MAX_SIZE_ERROR("buyeroutsideremark_max_size_error"),
    /**
     * 收货日期只能是今天及以前
     */
    RECEIVE_DATE_ERROR("receive_date_error"),
    /**
     * 发货子单状态变化不允许收货
     */
    RECEIVE_STATUS_ERROR_BY_SEND_GOODS_NOTE("receive_status_error_by_send_goods_note"),
    /**
     * 发货子单不允许超收
     */
    RECEIVE_QUANTITY_ERROR_BY_SEND_GOODS_NOTE("receive_quantity_error_by_send_goods_note"),
    /**
     * 采购子订单不允许超收
     */
    RECEIVE_QUANTITY_ERROR_BY_ORDER("receive_quantity_error_by_order"),
    /**
     * 卖家附言不能太长
     */
    SUPPLIERREMARK_MAX_SIZE_ERROR("supplierremark_max_size_error"),
    /**
     * 总金额不等于明细金额
     */
    TOTAL_AMOUNT_IS_NOT_EQUALS_TO_DETAILS("total_amount_is_not_equals_to_details"),
    /**
     * 总金额不等于渠道金额
     */
    TOTAL_AMOUNT_IS_NOT_EQUALS_TO_CHANNELS("total_amount_is_not_equals_to_channels"),
    /**
     * 核销类型错误
     */
    INVALID_MATCH_TYPE("invalid_match_type"),
    /**
     * 核销金额出错
     */
    FUND_MATCH_AMOUNT_ERROR("fund_match_amount_error"),
    /**
     * 核销模型为空
     */
    FUND_MATCH_IS_NULL("fund_match_is_null"),

    /**
     * 核销匹配规则为空
     */
    FUND_MATCH_RULE_IS_NULL("fund_match_rule_is_null"),
    /**
     * 更新核销单失败
     */
    UPDATE_FUND_MATCH_FAILED("update_fund_match_failed"),
    /**
     * 创建核销失败
     */
    CREATE_FUND_MATCH_FAILED("create_fund_match_failed"),
    /**
     * 来账/来票已经核销
     */
    FUND_MATCH_ALREADY_MATCHED("fund_match_already_matched"),
    /**
     * 执行交易核销失败
     */
    EXECUTE_TRADE_MATCH_FAILED("execute_trade_match_failed"),
    /**
     * 用户没有开通自动核销
     */
    USER_NOT_OPEN_FUND_MATCH("user_not_open_fund_match"),
    /**
     * 用户没有开启自动核销
     */
    USER_NOT_OPEN_INTELLIGENT_FUND_MATCH("user_not_open_intelligent_fund_match"),
    /**
     * 对公转账来账详情为空
     */
    FUND_TRANSBYBANK_DETAIL_IS_NULL("fund_transbybank_detail_is_null"),
    /**
     * 电票来票详情为空
     */
    FUND_MYBANKETICKET_DETAIL_IS_NULL("fund_mybanketicket_detail_is_null"),
    /**
     * 无效的资金类型
     */
    INVALID_FUND_TYPE("invalid_fund_type"),
    /**
     * 查询部门id失败
     */
    QUERY_DEPARTMENT_IDS_FAILED("query_department_ids_failed"),
    /**
     * 查询待核销付款单失败
     */
    LIST_WAIT_MATCH_FAILED("list_wait_match_failed"),
    /**
     * 来账/来票金额为空
     */
    FUND_MATCH_AMOUNT_IS_NULL("fund_match_amount_is_null"),
    /**
     * 核销filter失败
     */
    FUND_MATCH_DOFILTER_FAILED("fund_match_dofilter_failed"),
    /**
     * 核销sorter失败
     */
    FUND_MATCH_DOSORTER_FAILED("fund_match_dosorter_failed"),
    /**
     * 核销processor失败
     */
    FUND_MATCH_DOPROCESSOR_FAILED("fund_match_doprocessor_failed"),
    /**
     * 付款渠道不支持
     */
    PAY_CHANNEL_NOT_SUPPORTED("pay_channel_not_supported"),
    /**
     * 结算版本不支持
     */
    SETTLE_VERSION_NOT_SUPPORTED("settle_version_not_supported"),
    /**
     * 结算单已经打标
     */
    SETTLE_IS_PROCESS_FLAG("settle_is_process_flag"),
    /**
     * bizId类型不支持
     */
    BIZ_ID_NOT_SUPPORTED("biz_id_not_supported"),
    /**
     * 明细中含有不同的供应商
     */
    TOO_MUCH_SUPPLIERS("too_much_suppliers"),
    /**
     * 付款金额超过可付金额
     */
    PAY_AMOUNT_TOO_LARGE("pay_amount_too_large"),
    /**
     * 非账期订单必须先生成结算单
     */
    STEP_ORDER_NEED_CREATE_SETTLE_FIRST("step_order_need_create_settle_first"),
    /**
     * 批量提交付款单数量过多
     */
    TOO_MUCH_PAY("too_much_pay"),
    /**
     * 到账方式有误
     */
    TRANSFER_TYPE_ERROR("transfer_type_error"),
    /**
     * 付款提交参数为空
     */
    PAY_PARAM_IS_NULL("pay_param_is_null"),
    /**
     * 提交的付款明细为空
     */
    PAY_ENTRY_IS_NULL("pay_entry_is_null"),
    /**
     * 已存在付款单的requestNo
     */
    EXIST_PAY_REQUEST_NO("exist_pay_request_no"),
    /**
     * 已存在用户级付款单
     */
    EXIST_USER_PAY("exist_user_pay"),
    /**
     * 构建付款单的requestNo失败
     */
    BUILD_PAY_REQUEST_NO_FAILED("build_pay_request_no_failed"),
    /**
     * 提交付款单的结算信息有误
     */
    SUBMIT_PAY_SETTLE_DATA_ERROR("submit_pay_settle_data_error"),
    /**
     * 结算单已付款金额已经变化，不能提交付款单
     */
    SETTLEMENT_PAID_AMOUNT_CHANGED("settlement_paid_amount_changed"),
    /**
     * 结算单已付款
     */
    SETTLEMENT_IS_PAID("settlement_is_paid"),
    /**
     * 担保交易结算单必须全额付款
     */
    SETTLEMENT_SECURED_TRADE_MUST_PAY_ALL("settlement_secured_trade_must_pay_all"),
    /**
     * 终止付款单前，交易正在打款，提示稍后再重试确认终止
     */
    CONFIRM_STOP_PAY_RETRY_AFTER_DISBURSE("confirm_stop_pay_retry_after_disburse"),
    /**
     * 关闭交易结算单失败
     */
    CLOSE_TRADE_ORDER_FAILED("close_trade_order_failed"),
    /**
     * 终止结算单失败
     */
    TERMINATE_SETTLEMENT_FAILED("terminate_settlement_failed"),
    /**
     * 创建交易结算单失败
     */
    CREATE_TRADE_SETTLE_FAILED("create_trade_settle_failed"),
    /**
     * 取消完结付款单超时任务失败
     */
    CANCEL_STOP_PAY_TIMEOUT_FAILED("cancel_stop_pay_timeout_failed"),
    /**
     * 付款单金额错误
     */
    PAY_AMOUNT_ERROR("pay_amount_error"),
    /**
     * 结算单不存在
     */
    SETTLEMENT_NOT_EXIST("settlement_not_exist"),
    /**
     * 付款状态异常
     */
    PAY_STATUS_EXCEPTION("pay_status_exception"),
    /**
     * 创建系统级付款单失败
     */
    CREATE_SYSTEM_PAY_FAILED("create_system_pay_failed"),
    /**
     * 更新付款单失败
     */
    UPDATE_PAY_FAILED("update_pay_failed"),
    /**
     * 更新终止金额失败
     */
    UPDATE_STOP_AMOUNT_FAILED("update_stop_amount_failed"),
    /**
     * 终止未付款的付款单失败，配置中心未配置
     */
    STOP_PAY_NOTPAY_ERROR("stop_pay_notpay_error"),
    /**
     * 结算单更新失败
     */
    SETTLE_UPDATE_FAILED("settle_update_failed"),
    /**
     * 结算单迁移失败
     */
    SETTLE_TRANSFER_FAILED("settle_transfer_failed"),
    /**
     * 批量提交选择的结算单数量过多
     */
    TOO_MUCH_SETTLE("too_much_settle"),
    /**
     * 待结算金额为空
     */
    WAIT_SETTLE_AMOUNT_IS_NULL("wait_settle_amount_is_null"),
    /**
     * 数据所属人不同
     */
    DATA_OWNER_VALIDATE_FAILED("data_owner_validate_failed"),
    /**
     * 结算金额为空
     */
    SETTLE_PRICE_IS_NULL("settle_price_is_null"),
    /**
     * 采购订单结算状态不正确
     */
    INVALID_PO_SETTLE_STATUS("invalid_po_settle_status"),
    /**
     * 收货单结算状态不正确
     */
    INVALID_RECEIVE_SETTLE_STATUS("invalid_receive_settle_status"),
    /**
     * 结算数量为空
     */
    SETTLE_QUANTITY_IS_NULL("settle_quantity_is_null"),
    /**
     * 结算含税价格为空
     */
    SETTLE_TAX_PRICE_IS_NULL("settle_tax_price_is_null"),
    /**
     * 结算未税价格为空
     */
    SETTLE_NOTAX_PRICE_IS_NULL("settle_notax_price_is_null"),
    /**
     * 本次结算金额过大
     */
    SETTLE_PRICE_IS_TOO_LARGE("settle_price_is_too_large"),
    /**
     * 账期模式获取失败
     */
    SETTLE_QUERY_RULE_FAILED("settle_query_rule_failed"),
    /**
     * 账期结算收货单id为空
     */
    SETTLE_PERIOD_RECEIVE_ID_IS_NULL("settle_period_receive_id_is_null"),
    /**
     * 结算单状态不对不允许创建付款单
     */
    NOT_ALLOW_TO_CREATE_PAY_NOTE("not_allow_to_create_pay_note"),
    /**
     * 备注不能太长
     */
    REMARK_MAX_SIZE_ERROR("remark_max_size_error"),
    /**
     * 付款单总额不能超过100亿
     */
    PAY_AMOUNT_IS_TOO_LARGE("pay_amount_is_too_large"),
    /**
     * 查询收货子单失败
     */
    QUERY_RECEIVE_DETAIL_FAILED("query_receive_detail_failed"),
    /**
     * 收货子单对象为空
     */
    RECEIVE_DETAIL_IS_NULL("receive_detail_is_null"),
    /**
     * 查询结算单失败
     */
    QUERY_PROCUREMENT_SETTLEMENT_FAILED("query_procurement_settlement_failed"),
    /**
     * 收货子单总金额为空
     */
    RECEIVE_ENTRY_TOTAL_AMOUNT_IS_NULL("receive_entry_total_amount_is_null"),
    /**
     * 结算单为空
     */
    SETTLEMENT_IS_NULL("settlement_is_null"),
    /**
     * 结算单不符合终止条件
     */
    SETTLEMENT_CAN_NOT_TERMINATE("settlement_can_not_terminate"),
    /**
     * 结算单导出最大数量限制
     */
    SETTLEMENT_EXPORT_MAX_SIZE("settlement_export_max_size"),
    /**
     * 交易打款接口失败
     */
    TRADE_DISBURSE_FAILED("trade_disburse_failed"),
    /**
     * 结算单列表为空
     */
    SETTLEMENT_ENTRYS_IS_EMPTY("settlement_entrys_is_empty"),
    /**
     * 结算单开票失败
     */
    SETTLEMENT_MAKE_INVOICE_FAILED("settlement_make_invoice_failed"),
    /**
     * 结算单收票失败
     */
    SETTLEMENT_RECEIVE_INVOICE_FAILED("settlement_receive_invoice_failed"),
    /**
     * 收货单状态不是已退回
     */
    REJECT_RECEIVE_STATUS_ERROR("reject_receive_status_error"),
    /**
     * 收货单状态不是可编辑
     */
    EDIT_RECEIVE_STATUS_ERROR("edit_receive_status_error"),
    /**
     * 收货单确认失败
     */
    SENDGOODS_CONFIRM_FAILED("confirm_sendgoods_failed"),

    /**
     * 账期信用额度占用失败
     */
    PAYPERIOD_OCCUPY_FAIL("payperiod_occupy_fail"),

    /**
     * 账期信用额度释放失败
     */
    PAYPERIOD_RELEASE_FAIL("payperiod_release_fail"),

    /**
     * 订单快照太旧
     */
    ORDER_SNAPSHOT_TOO_OLD("order_snapshot_too_old"),

    /**
     * 订单版本错误
     */
    ORDER_VERSION_ERROR("order_version_error"),

    /**
     * 交易条款不能为空
     */
    TRADE_TERM_LIST_NOT_NULL("trade_term_list_not_null"),

    /**
     * tradeTerm为空
     */
    TRADE_TERM_IS_NULL("trade_term_is_null"),

    /**
     * 查询tradeTerm失败
     */
    QUERY_TRADE_TERM_FAILED("query_trade_term_failed"),

    /**
     * 交易条款金额与订单金额不等
     */
    TRADE_TERM_AMOUNT_NOT_EQ_TOTALCOST("trade_term_amount_not_eq_totalcost"),

    /**
     * 供应商不支持发票
     */
    SUPPLIER_NOT_SUPPORT_INVOICE("supplier_not_support_invoice"),


    /**
     * 发票地址不是有效的
     */
    INVOICE_ADDRESS_INVALID("invoice_address_invalid"),

    /**
     * 融易收未开通
     */
    EFT_ACCOUNT_NOT_OPENED("eft_account_not_opened"),

    /**
     * 融易收已开通但未绑定西进
     */
    EFT_ACCOUNT_NOT_BIDDING_XIJIN("eft_account_not_bidding_xijin"),

    /**
     * 订单总价小于等于0
     */
    TOTAL_COST_LESS_THAN_ZERO("total_cost_less_than_zero"),

    /**
     * excel解析失败
     */
    EXCEL_PRASE_FAILED("excel_prase_failed"),

    /**
     * excel写入失败
     */
    EXCEL_WRITE_FAILED("excel_write_failed"),

    /**
     * 创建在线任务失败
     */
    ONLIETASK_INIT_FAILED("onlinetask_init_failed"),

    /**
     * 采购订单不支持的交易类型
     */
    NOT_SUPPORT_TRADE_TYPE("not_support_trade_type"),

    /**
     * 全表扫描
     */
    TABLE_FULL_DATA_SCAN("table_full_data_scan"),
    /**
     * 交易方式错误
     */
    TRADE_MODE_ERROR("trade_mode_error"),
    /**
     * 当前引用
     */
    AT_PRESENT_QUOTE("at present_quote"),

    /**
     * 已停用
     */
    DISABLE("disable"),
    /*
     * 网商电票未开通
     */
    MYBANK_EBILL_NOT_OPEN("mybank_ebill_not_open"),
    /**
     * 电票已开通但未绑定西进
     */
    MYBANK_ACCOUNT_NOT_BIDDING_XIJIN("mybank_account_not_bidding_xijin"),
    /**
     * 供应商未开通电票/融易收/开通但未绑定西进 请更换其他付款方式
     */
    MYBANK_EBILL_OR_MYBANK_ACCOUNT_NOT_OPEN_OR_NOT_BIDDING_XIJIN("mybank_ebill_or_mybank_account_not_open_or_not_bidding_xijin"),
    /**
     * 不支持的支付方式类型
     */
    NOT_SUPPORT_PAYMENT_TYPE("not_support_payment_type"),
    /**
     * 创建付款单失败
     */
    SUBMIT_PAY_FAILED("submit_pay_failed"),
    /**
     * 没有收到来账
     */
    NO_FUND_TRANSFER_FOUND("no_fund_transfer_found"),
    /**
     * 大于10亿
     */
    TOTAL_COST_GT_ONE_HUNDRED_MILLION("totalcost_gt_one_hundred_million"),
    /**
     * 配置不允许返回null对象
     */
    CONFIG_NOT_ALLOW_RETURN_NULL_VALUE("config_not_allow_return_null_value"),

    /**
     * 技术状态INIT
     */
    TECH_STATUS_EQ_INIT("tech_status_eq_init"),
    /**
     * 技术状态PROCESSING
     */
    TECH_STATUS_EQ_PROCESSING("tech_status_eq_processing"),
    /**
     * 支付类型错误
     */
    PAYMENT_MODE_ERROR("payment_mode_error"),

    /**
     * 快照内容为空
     */
    SNAPSHOT_CONTENT_IS_NULL("snapshot_content_is_null"),

    /**
     * 商城快照为空
     */
    MALL_SNAPSHOT_IS_NULL("mall_snapshot_is_null"),

    /**
     * 发票模版错误
     */
    INVOICE_TEMPLATE_ERROR("invoice_template_error"),
    /**
     * 无结果
     */
    NO_RESULT("no_result"),

    /**
     * 延时任务状态非法
     */
    TIMED_TASK_STATUS_ILLEGAL("TIMED_TASK_STATUS_ILLEGAL"),

    /**
     * 延时任务已存在
     */
    TIMED_TASK_EXIST("TIMED_TASK_EXIST"),

    /**
     * 退货类型不支持
     */
    RETURN_GOODS_TYPE_NOT_SUPPORT("return_goods_type_not_support"),
    /**
     * 缺失物流信息
     */
    MISSING_LOGISTICS_INFO("missing_logistics_info"),
    /**
     * 待完善物流信息
     */
    WAITING_COMPLETE_LOGISTICS_INFO("waiting_complete_logistics_info"),
    /**
     * 物流信息长度超过最大限制
     */
    LOGISTICS_INFO_LENGTH_IS_TOO_LONG("logistics_info_length_is_too_long"),
    /**
     * 催款单据重复
     */
    PROMPT_REPEAT("prompt_repeat"),
    /**
     * 催款单号不存在
     */
    PROMPT_ENTITY_MISSING("prompt_entry_missing"),

    /**
     * 电票批量操作超过最大限制
     */
    E_TICKET_MAX_BATCH_SIZE_ERROR("e_ticket_max_batch_size_error"),

    /**
     * 电票提交核销数量出错
     */
    E_TICKET_FUND_MATCH_SIZE_ERROR("e_ticket_fund_match_size_error"),

    /**
     * 电票提交核销金额出错
     */
    E_TICKET_FUND_MATCH_AMOUNT_ERROR("e_ticket_fund_match_amount_error"),

    /**
     * 电票提交核销失败
     */
    E_TICKET_FUND_MATCH_EXECUTE_FAILED("e_ticket_fund_match_execute_failed"),

    /**
     * 电票超付
     */
    EXIST_SURPLUS_TICKET("exist_surplus_ticket"),
    /**
     * 数据已存在
     */
    DUPLICATE_KEY_EXCEPTION("duplicate_key_exception"),

    /**
     * 存在复制的订单
     */
    EXIST_COPY_ORDER("exist_copy_order"),

    /**
     * 该订单为是复制的订单
     */
    IS_COPY_ORDER("is_copy_order"),

    APPROVE_ENTRY_BATCH_MAX_SIZE_ERROR("apr_entry_batch_max_size_error"),

    /**
     * 仅仅支持担保交易和账期
     */
    ONLY_SUPPORT_ALIPAY_OR_PAYPERIOD("only_support_alipay_or_payperiod"),

    /**
     * 扣减失败
     */
    DEDUCT_FAILED("deduct_failed"),
    /*
     * 配送人电话为空
     */
    DELIVERY_MAN_MOBILE_IS_EMPTY("delivery_man_mobile_is_empty"),

    /**
     * 配送人电话格式不正确
     */
    DELIVERY_MAN_MOBILE_IS_ERROR("delivery_man_mobile_is_error"),

    /**
     * 配送人为空
     */
    DELIVERY_MAN_NAME_IS_EMPTY("delivery_man_name_is_empty"),

    /**
     * 电票批量操作超过最大限制
     */
    E_TICKET_PAY_AMOUNT_LIMIT_ERROR("e_ticket_pay_amount_limit_error"),

    /**
     * 都是审批驳回的物料提交提示文案
     */
    ALL_OF_MATERIAL_ARE_APPROVE_REJECTED("all_of_material_are_approve_rejected"),

    // 单次操作子单数上限错误
    ENTRY_MAX_SIZE_LIMIT_ERROR("entry_max_size_limit_error"),

    //订单重复
    ORDER_REPEAT("order_repeat"),

    /**
     * 订单导出数量超出限制
     */
    ORDER_EXPORT_MAX_SIZE("order_export_max_size"),

    /**
     * 电票金额不符合条件
     */
    TICKET_AMOUNT_NOT_ENOUGH("Ticket Amount Not Enough"),

    /**
     * 单据已经付款完成
     */
    ALREADY_PAYMENT_COMPLETED("already_payment_completed"),
    /**
     * 电票已被使用
     */
    TICKET_RELATED_OTHERS("Ticket Related Others"),
    /**
     * 运费计算失败
     */
    CARRIAGE_AMOUNT_ERROR("carriage_amount_error"),
    /**
     * 结算单业务类型错误
     */
    SETTLE_INVALID_BIZ_TYPE("settle_invalid_biz_type"),
    /**
     * 结算金额错误
     */
    SETTLE_AMOUNT_ERROR("settle_amount_error"),
    /**
     * 订单额外信息为空，内部商城结算单使用
     */
    ENTRY_EXTENDS_DATA_IS_NULL("entry_extends_data_is_null"),
    /**
     * 结算日为空
     */
    PERIOD_DAY_IS_NULL("period_day_is_null"),
    /**
     * 支付方式未开通
     */
    PAYMENT_NOT_OPEN("payment_not_open"),

    /**
     * 无可用支付方式
     */
    NOT_AVAILABLE_PAYMENT_TYPE("not_available_payment_type"),

    /**
     * 资金隔离未开启
     */
    FUNDHOLDABLE_IS_NOT_OPEN("fundHoldable_is_not_open"),
    /**
     * 融易收资料已过期
     */
    EFT_CERTIFICATE_EXPIRED("eft_certificate_expired"),

    /**
     * 过期提示信息
     */
    EFT_EXPIRE_CONTENT("eft_expire_content"),

    /**
     * 发货完成时间天数不够
     */
    SENT_GOODS_COMPLETED_NOT_ENOUGH_DAYS("sent_goods_completed_not_enough_days"),

    /**
     * 支付宝未开通
     */
    ALIPAY_ACCOUNT_NOT_OPEN("alipay_account_not_open"),
    /**
     * 对私转账未开通
     */
    PEFT_ACCOUNT_NOT_OPENED("peft_account_not_opened"),
    /**
     * 付款状态不是未付款
     */
    PAY_STATUS_NOT_WAIT_PAY("pay_status_not_wait_pay"),

    /**
     * 已被列入黑名单
     */
    HAVE_BEEN_BLACKLISTED("you_have_been_blacklisted"),

    /**
     * 库存扣减失败
     */
    INVENTORY_DEDUCTION_FAILED("inventory_deduction_failed"),

    /**
     * 底层服务RPC调用超时
     */
    RPC_TIMEOUT("rpc_timeout"),

    /**
     * 数据已发生变化
     */
    DATA_ALREADY_CHANGED("data_already_changed"),

    /**
     * 订单状态不是执行中
     */
    ORDER_STATUS_IS_NOT_APPROVED("order_status_is_not_approved"),

    /**
     * 乐观锁失败，数据已发生变化
     */
    OPTIMISTIC_LOCK_FAIL("optimistic_lock_fail"),

    /**
     * 卖家银企付未开通
     */
    BANK_PAY_NOT_OPENED("bank_pay_not_opened"),
    /**
     * 买家银企付暂不支持
     */
    BANK_PAY_NOT_SUPPORT("bank_pay_not_support"),

    /**
     * 卖家云企付未开通
     */
    SUPPLIER_YQF_NOT_OPEN("supplier_YQF_not_open"),

    /**
     * 买家云企付未开通
     */
    BUYER_YQF_NOT_OPEN("buyer_YQF_not_open"),

    /**
     * 商家续约已到期
     */
    MERCHANTS_CONTRACT_OVERDUE("merchants_contract_overdue"),
    /**
     * 订单终止中
     */
    ORDER_TERMINATING("order_terminating"),
    /**
     * 直营订单不允许取消
     */
    ORDER_PROPRIETARY_NO_CANCEL("order_proprietary_no_cancel"),
    /**
     * 订单物料下单量小于对应商品起订量moq
     */
    ORDER_AMOUNT_LESS_GOODS_MOQ("order_amount_less_goods_moq"),
    /**
     * 供应商为空/不存在
     */
    SUPPLIER_IS_NULL("supplier_is_null"),
    /**
     * 未自动匹配到商品sku规格
     */
    AUTO_MATCH_GOODS_SKU_FAIL("auto_match_goods_sku_fail"),
    /**
     * 商城商品由于下单量变化(创建/提交)导致阶梯单价变化，修改失败
     */
    MODIFY_OUTER_PROCUREMENT_ORDER_MATERIAL_PRICE_FAIL("modify_outer_procurement_order_material_price_fail"),
    /**
     * 对公转账存在相同待付款金额的付款单
     */
    FUND_TRANSBYBANK_SAME_WAIT_PAY_AMOUNT("fund_transbybank_same_wait_pay_amount"),
    /**
     * 开启资金隔离但未配置部门相应付款银行账户
     */
    FUND_ISOLATION_NO_SET_PAYER_BANK_ACCOUNT("fund_isolation_no_set_payer_bank_account"),
    /**
     * 启动审批流失败
     */
    START_WORKFLOW_FAIL("start_workflow_fail"),
    /**
     * 取消审批流失败
     */
    CANCEL_WORKFLOW_FAIL("cancel_workflow_fail"),
    /**
     * 获取审批流失败
     */
    GET_WORKFLOW_FAIL("get_workflow_fail"),
    /**
     * 数据同步发生错误
     */
    DATA_SYNC_WRONG("data_sync_wrong"),
    /**
     * 创建收货单失败
     */
    CREATE_RECVGOODS_FAIL("create_recvgoods_fail"),
    /**
     * 在用户当前部门权限下没有可被选择核销的付款单
     */
    CAN_CHOOSE_MATCH_PAY_NULL_BASE_DEPARTMENT_PERMISSION("can_choose_match_pay_null_base_department_permission"),
    /**
     * 选择核销时待付款核销的付款单集合不能为空
     */
    CHOOSE_MATCH_PAY_IS_NULL("choose_match_pay_is_null"),
    /**
     * 查询订单评价信息失败
     */
    QUERY_ORDERRATE_ERROR("query_orderrate_error"),
    /**
     * 订单评价字数超出最大限制
     */
    ORDER_RATE_CONTENT_MAX_LENGTH_ERROR("order_rate_content_max_length_error"),
    /**
     * 订单行计价公式状态无效
     */
    ORDER_ENTRY_PRICINGFORMULA_STATUS_ERROR("order_entry_pricingFormula_status_error"),
    /**
     * 订单行未进行公式计算
     */
    ORDER_ENTRY_PRICINGFORMULA_NOT_CALCULATE("order_entry_pricingFormula_not_calculate"),
    /**
     * 未查询到订单行公式
     */
    ORDER_ENTRY_PRICINGFORMULA_IS_NULL("order_entry_pricingFormula_is_null");


    ErrorCodeEnum(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private String errorCode;

}
