//
//  EdgeDetection.h
//  AVS_ParkingLot
//
//  Created by Robert Scherbarth on 15.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#include <math.h>
#include <opencv/cv.h>
#include <opencv/highgui.h>


@interface EdgeDetection : NSObject

-(IplImage*)probabilisticHough:(IplImage*) src;
-(IplImage*)canny:(IplImage*) src;

@end
