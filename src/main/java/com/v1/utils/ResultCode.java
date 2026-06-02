package com.v1.utils;

/**
 * 统一响应状态码枚举
 */
@SuppressWarnings("all")  // 移到类上
public enum ResultCode {
    
    // ==================== 成功状态码 ====================
    SUCCESS(200, "请求成功"),
    LOGOUT_SUCCESS(200, "注销成功"),  // 同一大类可以共用200
    
    // ==================== 分页相关 ====================
    PAGE_SUCCESS(200, "分页请求成功"),  // 200系列统一为成功
    
    // ==================== 注册登录相关 ====================
    REGISTER_SUCCESS(201, "注册成功"),
    LOGIN_SUCCESS(202, "登录成功"),
    USERNAME_NO_REPEAT(203, "用户名可用"),
    
    // ==================== 操作成功 ====================
    ADD_FAV_SUCCESS(210, "收藏成功"),
    REMOVE_SUCCESS(211, "移除成功"),
    
    // ==================== 客户端错误 400-499 ====================
    NO_LOGIN(401, "请先登录"),
    NO_RIGHTS(403, "暂无权限操作"),
    NO_CHECK(405, "验证失败"),
    
    // ==================== 参数错误 9000-9099 ====================
    CODE_FAIL(9001, "验证码错误"),
    LOGIN_FAIL(9002, "用户名或密码错误"),
    REPASSWORD_ERROR(9003, "两次密码不一致"),
    SAME_PASSWORD(9004, "新密码不能和原密码相同"),
    PASSWORD_ERROR(9005, "原密码错误"),
    PASSWORD_EMPTY(9006, "密码不能为空"),
    USERNAME_REPEAT(9007, "用户名重复"),
    REGISTER_FAIL(9008, "注册失败"),
    LOGIN_FORBID(9009, "用户被禁用"),
    
    // ==================== 业务错误 800-899 ====================
    NO_DELETE(800, "数据在使用中，请勿删除"),
    NO_DELETE_PARENT(801, "父级分类下存在子分类，请勿删除"),
    STOCK_NO(802, "库存不足"),
    NO_ONLINE(803, "商品未审核通过，不允许上下架"),
    CART_MAX_LIMIT(804, "该商品在购物车中数量最多为200"),
    
    // ==================== 服务器错误 ====================
    FAIL(500, "请求失败"),
    TIMEOUT(505, "支付超时");
    
    private final int code;
    private final String msg;
    
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    /**
     * 根据状态码获取枚举
     */
    public static ResultCode getByCode(int code) {
        for (ResultCode value : ResultCode.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
    
    /**
     * 判断是否成功（2xx系列）
     */
    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }
    
    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + msg + "\"" +
                '}';
    }
}