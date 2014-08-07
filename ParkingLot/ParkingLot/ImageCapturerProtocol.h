//
//  ImageCapturerProtocol.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <opencv2/opencv.hpp>
#import <Foundation/Foundation.h>

@protocol ImageCapturerProtocol <NSObject>

@required
- (void)addImageCapturerWorker:(NSHost*)worker;
- (void)removeImageCapturerWorker:(NSHost*)worker;
- (oneway void)forwardImage:(NSImage*)image
                 fromWorker:(NSHost*)worker;

@end
