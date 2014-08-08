//
//  NSImage_OpenCV.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface NSImage (NSImage_OpenCV)

+(NSImage*)imageWithCVMat:(const cv::Mat&)cvMat;

@end
