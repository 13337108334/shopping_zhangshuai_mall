<!--
 * 支付页面
 *
 * @author: ZhangDaYe
 * @Date: 2023-11-11
-->
<template>
<div style="margin-top: 10px;width: 50%;margin: 10px auto;background-color: white;text-align: center">
  <table style="margin: 20px auto">
    <tr>
      <th>订单号</th>
      <td>{{orderNo}}</td>
    </tr>

    <tr>
      <th>金额</th>
      <td>{{money}} 元</td>
    </tr>

  </table>
  <hr style="width: 280px" />
  <div>Ï、
    <span>支付方式：</span>
  </div>
  <br>
  <img src="../../../resource/img/微信支付.png" style="width: 45px;height: 45px;margin-right: 60px;cursor:pointer;" @click="wechatpay">
  <img src="../../../resource/img/支付宝.png" style="width: 45px;height: 45px;margin-right: 60px;cursor:pointer;" @click="alipay">
  <img src="../../../resource/img/人民币.png" style="width: 45px;height: 45px;margin-right: 60px;cursor:pointer;" @click="otherpay">
  <img src="../../../resource/img/银行.png" style="width: 45px;height: 45px;margin-right: 60px;cursor:pointer;" @click="transbankpay">
  <img src="../../../resource/img/电票.png" style="width: 45px;height: 45px;margin-right: 60px;cursor:pointer;" @click="mybankeftpay">
</div>
</template>

<script>
export default {
  name: "Pay",
  data() {
    return {
      userId: 0,
      money1: 0,
      orderNo: '',
      payType1: 'wechatpay',
      payType2: 'alipay',
      payType3: 'otherpay',
      payType4: 'transbankpay',
      payType5: 'mybankeftpay',
    }
  },
  created() {
    this.money = parseFloat(this.$route.query.money).toFixed(2);
    this.orderNo = this.$route.query.orderNo;
  },

  methods: {
    wechatpay() {
      this.request.get("/api/order/paid/" + this.orderNo + "/" + this.payType1).then(res => {
        if (res.code === '200') {
          alert("您成功支付" + this.money + "元")
          this.$router.replace("/orderlist")
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    alipay() {
      this.request.get("/api/order/paid/" + this.orderNo + "/" + this.payType2).then(res => {
        if (res.code === '200') {
          alert("您成功支付" + this.money + "元")
          this.$router.replace("/orderlist")
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    otherpay() {
      this.request.get("/api/order/paid/" + this.orderNo + "/" + this.payType3).then(res => {
        if (res.code === '200') {
          alert("您成功支付" + this.money + "元")
          this.$router.replace("/orderlist")
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    transbankpay() {
      this.request.get("/api/order/paid/" + this.orderNo + "/" + this.payType4).then(res => {
        if (res.code === '200') {
          alert("您成功支付" + this.money + "元")
          this.$router.replace("/orderlist")
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    mybankeftpay() {
      this.request.get("/api/order/paid/" + this.orderNo + "/" + this.payType5).then(res => {
        if (res.code === '200') {
          alert("您成功支付" + this.money + "元")
          this.$router.replace("/orderlist")
        } else {
          this.$message.error(res.msg)
        }
      })
    }

  }
}
</script>

<style scoped>
tr{
  line-height:40px;
}

</style>
