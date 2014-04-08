//
//  NSImage+IplImage.h
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#include <opencv/cv.h>

@interface NSImage (IplImage)

+ (NSImage*)imageWithIplImage:(IplImage *)image;

@end
