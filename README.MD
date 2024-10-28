# cordova-plugin-novar-bugly

腾讯 Bugly 的 Cordova 插件，支持 Android 和 iOS 平台。

## 安装

```bash
cordova plugin add cordova-plugin-novar-bugly
```

## 功能特性

- 崩溃收集上报
- 运行时异常捕获
- JS 异常监控
- 支持 Android 和 iOS 双平台

## 使用方法

### 初始化

```javascript
// 初始化 Bugly
cordova.plugins.CordovaBugly.init({
    androidAppId: "YOUR_ANDROID_APP_ID",
    iOSAppId: "YOUR_IOS_APP_ID"
}, success => {
    console.log('Bugly 初始化成功');
}, error => {
    console.error('Bugly 初始化失败:', error);
});
```

### 设置用户标识

```javascript
cordova.plugins.CordovaBugly.setUserId("用户ID");
```

### 上报自定义异常

```javascript
cordova.plugins.CordovaBugly.postException({
    name: "异常名称",
    reason: "异常原因",
    stack: "异常堆栈"
});
```

## 配置说明

### Android 权限

插件会自动添加以下必要权限：
- `android.permission.INTERNET`
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.ACCESS_WIFI_STATE`

### iOS 配置

插件会自动添加必要的框架支持：
- SystemConfiguration.framework
- Security.framework
- libz.tbd
- libc++.tbd
- Bugly.framework

## API 文档

### init(options, success, error)

初始化 Bugly SDK

| 参数 | 类型 | 说明 |
|------|------|------|
| options | Object | 配置选项 |
| options.androidAppId | String | Android 平台的 AppId |
| options.iOSAppId | String | iOS 平台的 AppId |
| success | Function | 成功回调 |
| error | Function | 失败回调 |

### setUserId(userId)

设置用户标识

| 参数 | 类型 | 说明 |
|------|------|------|
| userId | String | 用户唯一标识 |

### postException(error)

上报自定义异常

| 参数 | 类型 | 说明 |
|------|------|------|
| error.name | String | 异常名称 |
| error.reason | String | 异常原因 |
| error.stack | String | 异常堆栈 |

## 注意事项

1. 请确保在使用其他功能前先调用 `init` 方法完成初始化
2. AppId 请从 Bugly 后台获取
3. iOS 项目需要在 Xcode 中添加相应的 Framework 支持

## 问题反馈

如有问题或建议，请提交 Issue。

## 许可证

MIT License
