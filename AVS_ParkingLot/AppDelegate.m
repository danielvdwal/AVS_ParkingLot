//
//  AppDelegate.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import "AppDelegate.h"
#import "NSImage+IplImage.h"

@implementation AppDelegate

@synthesize window;
@synthesize image1View;
@synthesize image2View;
@synthesize image3View;
@synthesize image4View;
@synthesize cameraView;
@synthesize mode1Button;
@synthesize mode2Button;
@synthesize mode3Button;
@synthesize startButton;
@synthesize stopButton;
@synthesize textView;
@synthesize imageProcessor;
@synthesize imageViews;

- (id)init 
{
    self = [super init];
    if(self) {
        imageProcessor = [[ImageProcessor alloc] init];
        [imageProcessor setDelegate:self];
    }
    return self;
}

- (void)dealloc
{
    [super dealloc];
}

- (void)applicationDidFinishLaunching:(NSNotification *)notification
{
    self.imageViews = [NSArray arrayWithObjects:
                       self.image1View, 
                       self.image2View,
                       self.image3View,
                       self.image4View, 
                       nil];
}

- (void)showCameraImage:(NSImage *)image 
{
    [cameraView setImage:image];
}

- (void)showImages:(NSArray *)images 
{
    for(int i=0; i < images.count; i++) 
    {
        [((NSImageView*)[imageViews objectAtIndex:i]) setImage:[images objectAtIndex:i]];
    }
}

- (void)setText:(NSString*)text 
{
    [self.textView insertText:text];
    [self.textView insertText:@"\n"];
}

- (IBAction)mode1Clicked:(id)sender 
{
    [imageProcessor processImageWithFileName:@"/Users/danielvanderwal/Developer/AVS_ParkingLot/AVS_ParkingLot/parkingLot_template.jpg"];
}
- (IBAction)mode2Clicked:(id)sender 
{
    [imageProcessor processImageWithFileName:@"/Users/danielvanderwal/Developer/AVS_ParkingLot/AVS_ParkingLot/parkingLot_2_cars.jpg"];
}
- (IBAction)mode3Clicked:(id)sender 
{
    [imageProcessor processImageWithFileName:@"/Users/danielvanderwal/Developer/AVS_ParkingLot/AVS_ParkingLot/parkingLot_4_cars.jpg"];
}

- (IBAction)startClicked:(id)sender 
{
    if([imageProcessor canProcessCamera]) 
    {
        [imageProcessor startProcessingCamera];
    }
}
    
- (IBAction)stopClicked:(id)sender 
{
    [imageProcessor stopProcessingCamera];
    [self showCameraImage:nil];
}

@end

