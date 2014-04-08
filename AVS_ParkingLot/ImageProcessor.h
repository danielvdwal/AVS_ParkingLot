//
//  ImageProcessor.h
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
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

- (BOOL)startWithDelegate:(id)delegate;
- (void)stop;

@end
