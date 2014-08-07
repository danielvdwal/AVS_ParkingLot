//
//  NSImage_OpenCV.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "NSImage_OpenCV.h"

@interface NSImage (Private)

-(id)initWithCVMat:(cv::Mat&)cvMat;

@end

@implementation NSImage (NSImage_OpenCV)

+(NSImage*)imageWithCVMat:(cv::Mat &)cvMat {
    return [[NSImage alloc] initWithCVMat:cvMat];
}

-(id)initWithCVMat:(cv::Mat &)cvMat {
    cv::Mat arrMat = cvMat.clone();
    
    cv::cvtColor(cvMat, arrMat, CV_BGR2RGB);
    
    cvMat.release();
    
    CGColorSpaceRef colorspace = NULL;
    CGDataProviderRef provider = NULL;
    int width = arrMat.cols;
    int height = arrMat.rows;
    
    colorspace = CGColorSpaceCreateDeviceRGB();
    
    int size = 8;
    int nbChannels = 3;
    
    provider = CGDataProviderCreateWithData(NULL, arrMat.data, width * height , NULL );
    
    CGImageRef imageRef = CGImageCreate(width, height, size , size*nbChannels , arrMat.step, colorspace,  kCGImageAlphaNone , provider, NULL, true, kCGRenderingIntentDefault);
    
    NSBitmapImageRep *bitmap = [[NSBitmapImageRep alloc] initWithCGImage:imageRef];
    
    NSImage *image = [[NSImage alloc] init];
    [image addRepresentation:bitmap];
    
    CGDataProviderRelease(provider);
    CGImageRelease(imageRef);
    CGColorSpaceRelease(colorspace);
    
    arrMat.release();
    
    return image;
}

@end