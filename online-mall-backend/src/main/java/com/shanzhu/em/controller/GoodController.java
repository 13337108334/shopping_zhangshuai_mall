package com.shanzhu.em.controller;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanzhu.em.common.R;
import com.shanzhu.em.constants.Status;
import com.shanzhu.em.entity.Good;
import com.shanzhu.em.entity.Order;
import com.shanzhu.em.entity.Standard;
import com.shanzhu.em.entity.User;
import com.shanzhu.em.entity.vo.GoodVo;
import com.shanzhu.em.service.GoodService;
import com.shanzhu.em.service.StandardService;
import com.shanzhu.em.service.orderpay.PayLogic;
import com.shanzhu.em.utils.ErrorCodeAndMessage;
import com.shanzhu.em.utils.ResultData;
import com.shanzhu.em.utils.SourceBizTypeEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 商品 控制层
 */
@RestController
@RequestMapping("/api/good")
@RequiredArgsConstructor
public class GoodController {

    private static final Logger log = LoggerFactory.getLogger(GoodController.class);

    public static final Long ORDER_LONG_ID = 15L;

    @Autowired
    private final PayLogic payLogic;

    private final GoodService goodService;

    private final StandardService standardService;

    /**
     * 查询商品
     *
     * @param id 商品id
     * @return 商品
     */
    @GetMapping("/{id}")
    public R<Good> findGood(@PathVariable Long id) {
        return R.success(goodService.getGoodById(id));
    }

    /**
     * 注意！！！： 测试前将 该类定义的公用参数 ORDER_LONG_ID 第40行代码 改为你数据库 t_order表的id 再进行测试
     * 访问地址：http://localhost:8888/api/good/logic
     * 测试支付宝、微信、其他，这三种支付方式走策略模式代码
     *
     * @return 订单信息包被体
     */
    @GetMapping("/logic")
    public ResultData<Order> logicController() {
        log.info("TestController logicController -1 进入controller代码");
        SourceBizTypeEnum sourceBizTypeEnum1 = SourceBizTypeEnum.of("alipay");
        SourceBizTypeEnum sourceBizTypeEnum2 = SourceBizTypeEnum.of("wechatpay");
        SourceBizTypeEnum sourceBizTypeEnum3 = SourceBizTypeEnum.of("otherpay");
        SourceBizTypeEnum sourceBizTypeEnum4 = SourceBizTypeEnum.of("第四个取不到返回null 程序不能中断");

        List<SourceBizTypeEnum> sourceBizTypeEnumList = new ArrayList<>();
        sourceBizTypeEnumList.add(sourceBizTypeEnum1);
        sourceBizTypeEnumList.add(sourceBizTypeEnum2);
        sourceBizTypeEnumList.add(sourceBizTypeEnum3);
        sourceBizTypeEnumList.add(sourceBizTypeEnum4);
        log.info("TestController logicController -2 sourceBizTypeEnumList:{}", JSON.toJSONString(sourceBizTypeEnumList));
        if (CollectionUtils.isEmpty(sourceBizTypeEnumList)) {
            ResultData<Order> resultData = ResultData.genError(ErrorCodeAndMessage.SOURCE_LIST_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.SOURCE_LIST_IS_NULL.getErrorMessage());
            log.error("TestController logicController sourceBizTypeEnumList is null 程序返回结果为 result：{}",JSON.toJSONString(resultData));
            return resultData;
        }
        // 打乱集合顺序 枚举随机从集合取第一个值作为一个随机来源类型向下传递
        Collections.shuffle(sourceBizTypeEnumList);
        SourceBizTypeEnum sourceBizTypeEnum = sourceBizTypeEnumList.get(0);
        log.error("TestController logicController sourceBizTypeEnum:{}", JSON.toJSONString(sourceBizTypeEnum));
        if (sourceBizTypeEnum == null) {
            ResultData<Order> resultData = ResultData.genError(ErrorCodeAndMessage.SOURCE_BIZTYPE_ENUMLIST_IS_NULL.getStringErrorCode(), ErrorCodeAndMessage.SOURCE_BIZTYPE_ENUMLIST_IS_NULL.getErrorMessage());
            log.error("TestController logicController sourceBizTypeEnum来源类型随机取到了null 程序返回结果为 result：{}",JSON.toJSONString(resultData));
            return resultData;
        }
        log.info("TestController logicController -3 sourceBizTypeEnum:{},sourceBizTypeEnum.value:{}", JSON.toJSONString(sourceBizTypeEnum), JSON.toJSONString(sourceBizTypeEnum.getValue()));
        return payLogic.logic(sourceBizTypeEnum, ORDER_LONG_ID);
    }

    /**
     * 查询商品规格
     *
     * @param id 商品规格id
     * @return 商品规格
     */
    @GetMapping("/standard/{id}")
    public R<String> getStandard(@PathVariable Integer id) {
        return R.success(goodService.getStandard(id));
    }

    /**
     * 查询推荐商品，即recommend=1
     *
     * @return 商品
     */
    @GetMapping
    public R<GoodVo> findFrontGoods() {
        return R.success(goodService.findFrontGoods());
    }

    /**
     * 商品销售额排行
     *
     * @return 商品
     */
    @GetMapping("/rank")
    public R<List<Good>> getSaleRank(@RequestParam int num) {
        return R.success(goodService.getSaleRank(num));
    }

    /**
     * 保存商品
     *
     * @param good 商品信息
     * @return 商品id
     */
    @PostMapping
    public R<Long> save(@RequestBody Good good) {
        return R.success(goodService.saveOrUpdateGood(good));
    }

    /**
     * 更新商品
     *
     * @param good 商品信息
     */
    @PutMapping
    public R<Void> update(@RequestBody Good good) {
        goodService.update(good);
        return R.success();
    }

    /**
     * 删除商品
     *
     * @param id 商品id
     */
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        goodService.loginDeleteGood(id);
        return R.success();
    }

    /**
     * 保存商品规格
     *
     * @param standards 商品规格列表
     * @param goodId    商品id
     */
    @PostMapping("/standard")
    public R<Void> saveStandard(
            @RequestBody List<Standard> standards,
            @RequestParam int goodId
    ) {
        //先删除全部旧记录
        standardService.deleteAll(goodId);

        //然后插入新记录
        for (Standard standard : standards) {
            standard.setGoodId(goodId);
            if (!standardService.save(standard)) {
                return R.error(Status.CODE_500, "商品id: " + goodId + " ,规格保存失败");
            }
        }

        return R.success();
    }

    /**
     * 删除商品规格
     *
     * @param standard 商品规格列表
     */
    @DeleteMapping("/standard")
    public R<Void> delStandard(@RequestBody Standard standard) {
        if (BooleanUtil.isTrue(standardService.delete(standard))) {
            return R.success();
        } else {
            return R.error(Status.CODE_500, "删除失败");
        }
    }

    /**
     * 修改商品推荐
     *
     * @param id          商品id
     * @param isRecommend 是否推荐
     * @return 结果
     */
    @GetMapping("/recommend")
    public R<Void> setRecommend(
            @RequestParam Long id,
            @RequestParam Boolean isRecommend
    ) {
        return R.success(goodService.setRecommend(id, isRecommend));
    }

    /**
     * 分页查询商品 - 带查询条件
     *
     * @param pageNum    页数
     * @param pageSize   分页大学
     * @param searchText 查询文本
     * @param categoryId 分类id
     * @return 商品列表
     */
    @GetMapping("/page")
    public R<IPage<GoodVo>> findGoodPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String searchText,
            @RequestParam(required = false) Integer categoryId
    ) {
        return R.success(
                goodService.findPage(pageNum, pageSize, searchText, categoryId)
        );
    }

    /**
     * 分页查询全部商品
     *
     * @param pageNum    页数
     * @param pageSize   分页大学
     * @param searchText 查询文本
     * @param categoryId 分类id
     * @return 商品列表
     */
    @GetMapping("/fullPage")
    public R<IPage<GoodVo>> findGoodFullPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String searchText,
            @RequestParam(required = false) Integer categoryId) {

        return R.success(goodService.findFullPage(pageNum, pageSize, searchText, categoryId));
    }

}
