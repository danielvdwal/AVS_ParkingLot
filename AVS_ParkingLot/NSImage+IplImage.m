//
//  NSImage+IplImage.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "NSImage+IplImage.h"

@implementation NSImage (IplImage)

+ (NSImage*)imageWithIplImage:(IplImage *)image {
    
    IplImage *rgbImage = cvCreateImage(cvSize(image->width, image->height), image->depth, image->nChannels);
    cvCvtColor(image, rgbImage, CV_BGR2RGB);
    
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    // Allocating the buffer for CGImage
    NSData *data = [NSData dataWithBytes:rgbImage->imageData length:rgbImage->imageSize];
    CGDataProviderRef provider = CGDataProviderCreateWithCFData((CFDataRef)data);
    // Creating CGImage from chunk of IplImage
    CGImageRef imageRef = CGImageCreate(rgbImage->width,
                                        rgbImage->height,
                                        rgbImage->depth,
                                        rgbImage->depth * rgbImage->nChannels,
                                        rgbImage->widthStep,
                                        colorSpace,
                                        kCGImageAlphaNone|kCGBitmapByteOrderDefault,
                                        provider,
                                        NULL,
                                        false,
                                        kCGRenderingIntentDefault);
    // Getting UIImage from CGImage
    NSSize size;
    size.height = rgbImage->height;
    size.width = rgbImage->width;
    NSImage *ret = [[NSImage alloc] initWithCGImage:imageRef size:size];
    CGImageRelease(imageRef);
    CGDataProviderRelease(provider);
    CGColorSpaceRelease(colorSpace);
    
    return ret;
}
@end
