
#import <Cordova/CDVViewController.h>
#import <Bugly/Bugly.h>
#import "CordovaBugly.h"

@implementation CordovaBugly

- (void)pluginInitialize
{
    NSString* appid = ((CDVViewController *)self.viewController).settings[@"buglyiosappid"];
    
    if (appid == nil || [appid length] == 0) {
        NSLog(@"BuglyIOSAppId not found in config.xml");
    } else {
        NSLog(@"BuglyIOSAppId: %@", appid);
        // 使用 Bugly 初始化
        [Bugly startWithAppId:appid];
    }
}

- (void)testCrash:(CDVInvokedUrlCommand*)command{
    NSString* appid = [self.commandDelegate.settings objectForKey:@"BuglyIOSAppId"];
    NSLog(@"appid: %@", appid);
    [self testNSException];
    [self testSignalException];
}

- (void)testNSException {
    NSLog(@"it will throw an NSException ");
    NSArray * array = @[];
    NSLog(@"the element is %@", array[1]);
}

- (void)testSignalException {
    NSLog(@"test signal exception");
    NSString * null = nil;
    NSLog(@"print the nil string %s", [null UTF8String]);
}

@end
