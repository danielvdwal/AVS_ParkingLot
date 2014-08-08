//
//  WrappedImage.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 08.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface WrappedImage : NSObject {
    @private
    cv::Mat _cvMat;
}

- (id)initWithCvMat:(const cv::Mat&)mat;
- (cv::Mat)cvMat;

@end
