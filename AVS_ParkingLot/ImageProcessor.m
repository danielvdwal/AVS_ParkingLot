//
//  ImageProcessor.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import "ImageProcessor.h"
#import "NSImage+IplImage.h"

@implementation ImageProcessor

@synthesize capture;
@synthesize workerThread;
@synthesize delegate;

-(id)init {
    self = [super init];
    if (self) {
    }
    return self;
}

-(void)processImageWithFileName:(NSString*)fileName {
    IplImage* blank = cvLoadImage("/Users/danielvanderwal/Developer/AVS_ParkingLot/AVS_ParkingLot/parkingLot.jpg",0);
    IplImage* blankParkingSpot01 = cvCreateImage(cvSize(180,260), blank->depth, blank->nChannels);
    IplImage* blankParkingSpot02 = cvCreateImage(cvSize(180,260), blank->depth, blank->nChannels);
    IplImage* blankParkingSpot03 = cvCreateImage(cvSize(180,260), blank->depth, blank->nChannels);
    IplImage* blankParkingSpot04 = cvCreateImage(cvSize(180,260), blank->depth, blank->nChannels);
    IplImage *(blankParkingSpots[]) = { blankParkingSpot01, blankParkingSpot02, blankParkingSpot03, blankParkingSpot04};

    
    IplImage* src = cvLoadImage([fileName UTF8String] ,0);
    IplImage* parkingSpot01 = cvCreateImage(cvSize(180,260), src->depth, src->nChannels);
    IplImage* parkingSpot02 = cvCreateImage(cvSize(180,260), src->depth, src->nChannels);
    IplImage* parkingSpot03 = cvCreateImage(cvSize(180,260), src->depth, src->nChannels);
    IplImage* parkingSpot04 = cvCreateImage(cvSize(180,260), src->depth, src->nChannels);
    IplImage *(parkingSpots[]) = { parkingSpot01, parkingSpot02, parkingSpot03, parkingSpot04};
    
    int size = sizeof(parkingSpots) / sizeof(IplImage*);
    for(int i=0; i < size; i++) {
        // Say what the source region is 
        cvSetImageROI(blank, cvRect(i*160 + 40, 15, 180, 260)); 
        cvSetImageROI(src, cvRect(i*160 + 40, 15, 180, 260)); 
    
        // Do the copy 
        cvCopy(blank, blankParkingSpots[i], NULL); 
        cvCopy(src, parkingSpots[i], NULL); 
        cvResetImageROI(blank); 
        cvResetImageROI(src);
        
        char* blankImageData = blankParkingSpots[i]->imageData;
        char* imageData = parkingSpots[i]->imageData;
        double sum1 = 0;
        double sum2 = 0;
        for(int y=0; y < blankParkingSpots[i]->height; y++) {
            for(int x=0; x < blankParkingSpots[i]->width; x++) {
                sum1 += blankImageData[(y * blankParkingSpots[i]->widthStep) + (x*3)];
                sum1 += blankImageData[(y * blankParkingSpots[i]->widthStep) + (x*3)+1];
                sum1 += blankImageData[(y * blankParkingSpots[i]->widthStep) + (x*3)+2];
                sum2 += imageData[(y * parkingSpots[i]->widthStep) + (x*3)];
                sum2 += imageData[(y * parkingSpots[i]->widthStep) + (x*3)+1];
                sum2 += imageData[(y * parkingSpots[i]->widthStep) + (x*3)+2];
            }
        }
        if(sum1 > sum2) {
            if(sum1/sum2 < 0.98) {
                [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is occupied!", i+1]]; 
            } else {
                [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is free!", i+1]]; 
            }
        } else {
            if(sum2/sum1 < 0.98) {
                [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is occupied!", i+1]]; 
            } else {
                [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is free!", i+1]]; 
            }
        }
    }

    NSImage* image1 = [NSImage imageWithIplImage:parkingSpot01];
    NSImage* image2 = [NSImage imageWithIplImage:parkingSpot02];
    NSImage* image3 = [NSImage imageWithIplImage:parkingSpot03];
    NSImage* image4 = [NSImage imageWithIplImage:parkingSpot04];
    NSArray* images = [NSArray arrayWithObjects:image1, image2, image3, image4, nil];
    [self.delegate showImages:images];
}

-(BOOL)canProcessCamera {
    self.capture = cvCaptureFromCAM(-1);
    if (!self.capture) {
        NSLog(@"Cannot initialize webcam");
        return NO;
    }
    return YES;
}

-(void)startProcessingCamera {
    workerThread = [[NSThread alloc] initWithTarget:self
                                           selector:@selector(doWork:)
                                             object:nil];
    _continue = YES;
    [workerThread start];
}

-(void)stopProcessingCamera {
    _continue = NO;
    [workerThread cancel];
    cvReleaseCapture(&capture);
}

-(void)doWork:(id)arg {
    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    
    @autoreleasepool {
        while(_continue) {
            IplImage *capturedImage = cvQueryFrame(capture);
            NSImage *image = [NSImage imageWithIplImage:capturedImage];
            [self.delegate showCameraImage:image];
        }
    }
    [pool release];
}

@end
