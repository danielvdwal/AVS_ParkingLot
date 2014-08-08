//
//  WrappedImage.m
//  ParkingLot
//
//  Created by Daniel van der Wal on 08.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "WrappedImage.h"

@implementation WrappedImage

-(id)initWithCvMat:(const cv::Mat &)mat {
    if(self = [super init]) {
        mat.copyTo(_cvMat);
    }
    return self;
}

-(cv::Mat)cvMat {
    return _cvMat;
}

@end
