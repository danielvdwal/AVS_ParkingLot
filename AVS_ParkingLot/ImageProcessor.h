//
//  ImageProcessor.h
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ImageShower.h"

#include <opencv/cv.h>
#include <opencv/cxcore.h>
#include <opencv/highgui.h>

@interface ImageProcessor : NSObject {
    BOOL _continue;
}

@property (assign) CvCapture *capture;
@property (retain) NSThread* workerThread;
@property (assign) id<ImageShower> delegate;

- (void)processImageOnTemplateMethodWithFileName:(NSString*)fileName;
- (void)processImageOnObjectDetectionMethodWithFileName:(NSString *)fileName 
                                       WithThresholdMin:(int)min
                                        AndThresholdMax:(int)max;
- (BOOL)canProcessCamera;
- (void)startProcessingCamera;
- (void)stopProcessingCamera;

@end
