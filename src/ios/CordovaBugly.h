#import <Cordova/CDVPlugin.h>

@interface CordovaBugly : CDVPlugin {

}
- (void)testCrash:(CDVInvokedUrlCommand*)command;

@end