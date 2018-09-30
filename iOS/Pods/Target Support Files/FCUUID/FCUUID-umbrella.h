#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "FCUUID.h"
#import "UIDevice+FCUUID.h"

FOUNDATION_EXPORT double FCUUIDVersionNumber;
FOUNDATION_EXPORT const unsigned char FCUUIDVersionString[];

