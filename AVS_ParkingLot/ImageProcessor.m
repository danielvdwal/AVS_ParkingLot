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
        workerThread = [[NSThread alloc] initWithTarget:self
                                               selector:@selector(doWork:)
                                                 object:nil];
    }
    return self;
}

-(BOOL)startWithDelegate:(id)aDelegate {
    self.delegate = aDelegate;
    self.capture = cvCaptureFromCAM(-1);
    if (!self.capture) {
        NSLog(@"Cannot initialize webcam");
        return NO;
    }
    _continue = YES;
    [workerThread start];
    return YES;
}

-(void)stop {
    _continue = NO;
    if(self.capture) {
        cvReleaseCapture(&capture);
    }
}


-(void)doWork:(id)arg {
    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    
    @autoreleasepool {
        while(_continue) {
            IplImage *capturedImage = cvQueryFrame(capture);
            NSImage *image = [NSImage imageWithIplImage:capturedImage];
            [self.delegate showImage:image];
        }
    }
    [pool release];
}

@end
