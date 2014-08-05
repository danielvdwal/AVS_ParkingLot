//
//  NSImage+IplImage.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import "NSImage+IplImage.h"

@implementation NSImage (IplImage)

+ (NSImage*)imageWithIplImage:(IplImage *)image {
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceGray();
    // Allocating the buffer for CGImage
    NSData *data = [NSData dataWithBytes:image->imageData length:image->imageSize];
    CGDataProviderRef provider = CGDataProviderCreateWithCFData((CFDataRef)data);
    // Creating CGImage from chunk of IplImage
    CGImageRef imageRef = CGImageCreate(image->width, image->height, image->depth, 
                                        image->depth * image->nChannels, image->widthStep,
                                        colorSpace, kCGImageAlphaNone|kCGBitmapByteOrderDefault,
                                        provider, NULL, false, kCGRenderingIntentDefault);
    // Getting UIImage from CGImage
    NSSize size;
    size.height = image->height;
    size.width = image->width;
    NSImage *ret = [[NSImage alloc] initWithCGImage:imageRef size:size];
    CGImageRelease(imageRef);
    CGDataProviderRelease(provider);
    CGColorSpaceRelease(colorSpace);
    
    return ret;
}
@end
