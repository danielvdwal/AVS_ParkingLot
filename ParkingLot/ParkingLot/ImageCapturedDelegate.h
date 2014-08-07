//
//  ImageCapturedDelegate.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol ImageCapturedDelegate <NSObject>

@required
- (void)imageCaptured:(cv::Mat)image;

@end
