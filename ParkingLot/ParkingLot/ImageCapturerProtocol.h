//
//  ImageCapturerProtocol.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "WrappedImage.h"

@protocol ImageCapturerProtocol <NSObject>

@required
- (void)addImageCapturerWorker:(NSHost*)worker;
- (void)removeImageCapturerWorker:(NSHost*)worker;
- (void)forwardImage:(WrappedImage*)image
          fromWorker:(NSHost*)worker;

@end
