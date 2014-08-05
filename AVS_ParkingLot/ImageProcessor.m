//
//  ImageProcessor.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageProcessor.h"
#import "NSImage+IplImage.h"
#import "EdgeDetection.h"

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

-(void)processImageOnTemplateMethodWithFileName:(NSString*)fileName {
    IplImage* blank = cvLoadImage("AVS_ParkingLot.app/Contents/Resources/parkingLot_template.jpg",0);
    IplImage* blankParkingSpot01 = cvCreateImage(cvSize(170,260), blank->depth, blank->nChannels);
    IplImage* blankParkingSpot02 = cvCreateImage(cvSize(170,260), blank->depth, blank->nChannels);
    IplImage* blankParkingSpot03 = cvCreateImage(cvSize(170,260), blank->depth, blank->nChannels);
    IplImage* blankParkingSpot04 = cvCreateImage(cvSize(170,260), blank->depth, blank->nChannels);
    IplImage *(blankParkingSpots[]) = { blankParkingSpot01, blankParkingSpot02, blankParkingSpot03, blankParkingSpot04};

    
    IplImage* src = cvLoadImage([fileName UTF8String] ,0);
    IplImage* parkingSpot01 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* parkingSpot02 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* parkingSpot03 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* parkingSpot04 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage *(parkingSpots[]) = { parkingSpot01, parkingSpot02, parkingSpot03, parkingSpot04};
    
    int size = sizeof(parkingSpots) / sizeof(IplImage*);
    for(int i=0; i < size; i++) {
        // Say what the source region is 
        cvSetImageROI(blank, cvRect(i*158 + 45, 15, 170, 260)); 
        cvSetImageROI(src, cvRect(i*158 + 45, 15, 170, 260)); 
        
        // Do the copy 
        cvCopy(blank, blankParkingSpots[i], NULL); 
        cvCopy(src, parkingSpots[i], NULL); 
        cvResetImageROI(blank); 
        cvResetImageROI(src);
        
        double sum1 = 0;
        double sum2 = 0;
        
        char* blankImageData = blankParkingSpots[i]->imageData;
        char* imageData = parkingSpots[i]->imageData;
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
        
        NSLog(@"%f, %f", sum1, sum2 );
        if(sum1 > sum2) {
            if(sum1/sum2 < 0.70) {
                [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is occupied!", i+1]]; 
            } else {
                [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is free!", i+1]]; 
            }
        } else {
            if(sum2/sum1 < 0.70) {
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

-(void)processImageOnObjectDetectionMethodWithFileName:(NSString *)fileName 
                                      WithThresholdMin:(int)min
                                       AndThresholdMax:(int)max {
    IplImage* src = cvLoadImage([fileName UTF8String] ,0);
    IplImage* parkingSpot01 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* parkingSpot02 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* parkingSpot03 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* parkingSpot04 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage *(parkingSpots[]) = { parkingSpot01, parkingSpot02, parkingSpot03, parkingSpot04};
        
    IplImage* cannyParkingSpot01 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* cannyParkingSpot02 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* cannyParkingSpot03 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage* cannyParkingSpot04 = cvCreateImage(cvSize(170,260), src->depth, src->nChannels);
    IplImage *(cannyParkingSpots[]) = { cannyParkingSpot01, cannyParkingSpot02, cannyParkingSpot03, cannyParkingSpot04};
    
    EdgeDetection *edgeDec = [[EdgeDetection alloc] init];
    IplImage* edgeImg = [edgeDec probabilisticHough:src];
    
    int size = sizeof(parkingSpots) / sizeof(IplImage*);
    for(int i=0; i < size; i++) {
        // Say what the source region is 
        cvSetImageROI(src, cvRect(i*158 + 45, 15, 170, 260));
        
        // Do the copy
        cvCopy(src, parkingSpots[i], NULL);
        cvResetImageROI(src);
        
        cvCanny(parkingSpots[i], cannyParkingSpots[i], min, max, 3);
        
        CvScalar white = CV_RGB( 255, 255, 255 );
        
        CvMemStorage* storage = cvCreateMemStorage(0);
        CvSeq* contour = 0;
        
        cvFindContours(cannyParkingSpots[i], storage, &contour, sizeof(CvContour), CV_RETR_CCOMP, CV_CHAIN_APPROX_SIMPLE, cvPoint(0, 0) );
        
        for( ; contour != 0; contour = contour->h_next )
        {
            cvDrawContours( cannyParkingSpots[i], contour, white, white, 1, CV_FILLED, 8, cvPoint(0, 0));
        }

        double n = cvCountNonZero(cannyParkingSpots[i]);
        double x = cannyParkingSpots[i]->height * cannyParkingSpots[i]->width;
        NSLog(@"%f / %f", n, x);  
        if(n/x > 0.2) {
            [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is occupied!", i+1]]; 
        } else {
            [self.delegate setText:[NSString stringWithFormat:@"Parking slot %d is free!", i+1]]; 
        }
    }
        
    NSImage* image1 = [NSImage imageWithIplImage:cannyParkingSpot01];
    NSImage* image2 = [NSImage imageWithIplImage:cannyParkingSpot02];
    NSImage* image3 = [NSImage imageWithIplImage:cannyParkingSpot03];
    NSImage* image4 = [NSImage imageWithIplImage:cannyParkingSpot04];
    NSImage* edgeImage = [NSImage imageWithIplImage:edgeImg];
    NSArray* images = [NSArray arrayWithObjects:edgeImage, image2, image3, image4, nil];
    [self.delegate showImages:images];
}

-(BOOL)canProcessCamera {
    self.capture = cvCaptureFromCAM(0);
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
    @autoreleasepool {
        while(_continue) {
            IplImage *capturedImage = cvQueryFrame(capture);
            NSImage *image = [NSImage imageWithIplImage:capturedImage];
            capturedImage = nil;
            [self.delegate showCameraImage:image];
            cvWaitKey(33);
        }
    }
}

@end
