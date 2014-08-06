//
//  AppDelegate.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
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
@synthesize mode4Button;
@synthesize mode5Button;
@synthesize mode6Button;
@synthesize startButton;
@synthesize stopButton;
@synthesize textView;
@synthesize imageProcessor;
@synthesize imageViews;
@synthesize lastButtonPressed;
@synthesize minValueTextField;
@synthesize maxValueTextField;
@synthesize minValue;
@synthesize maxValue;

- (id)init
{
    self = [super init];
    if(self) {
        imageProcessor = [[ImageProcessor alloc] init];
        [imageProcessor setDelegate:self];
        minValue = 0;
        maxValue = 45;
    }
    return self;
}

- (void)applicationDidFinishLaunching:(NSNotification *)notification
{
    self.imageViews = [NSArray arrayWithObjects:
                       self.image1View,
                       self.image2View,
                       self.image3View,
                       self.image4View,
                       nil];
    
    [minValueTextField setStringValue:[NSString stringWithFormat:@"%d", minValue]];
    [maxValueTextField setStringValue:[NSString stringWithFormat:@"%d", maxValue]];
}

- (void)showCameraImage:(NSImage *)image
{
    //[cameraView setImage:image];
}

- (void)showImages:(NSArray *)images
{
    for(int i=0; i < images.count; i++)
    {
        //[((NSImageView*)[imageViews objectAtIndex:i]) setImage:[images objectAtIndex:i]];
    }
}

- (void)setText:(NSString*)text
{
    [self.textView insertText:text];
    [self.textView insertText:@"\n"];
}

- (IBAction)mode1Clicked:(id)sender
{
    lastButtonPressed = mode1Button;
    [imageProcessor processImageOnTemplateMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_template.jpg"];
}
- (IBAction)mode2Clicked:(id)sender
{
    lastButtonPressed = mode2Button;
    [imageProcessor processImageOnTemplateMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_2_cars.jpg"];
}
- (IBAction)mode3Clicked:(id)sender
{
    lastButtonPressed = mode3Button;
    [imageProcessor processImageOnTemplateMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_4_cars.jpg"];
}

- (IBAction)mode4Clicked:(id)sender {
    lastButtonPressed = mode4Button;
    [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_template.jpg" WithThresholdMin:0 AndThresholdMax:45];
}

- (IBAction)mode5Clicked:(id)sender {
    lastButtonPressed = mode5Button;
    [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_2_cars.jpg" WithThresholdMin:0 AndThresholdMax:45];
}

- (IBAction)mode6Clicked:(id)sender {
    lastButtonPressed = mode6Button;
    [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_4_cars.jpg" WithThresholdMin:0 AndThresholdMax:45];
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

- (IBAction)minSliderValueChanged:(id)sender {
    NSSlider *slider = (NSSlider *)sender;
    minValue = slider.intValue;
    [minValueTextField setStringValue:[NSString stringWithFormat:@"%d", minValue]];
    if(lastButtonPressed == mode4Button) {
        [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_template.jpg" WithThresholdMin:minValue AndThresholdMax:maxValue];
    } else if(lastButtonPressed == mode5Button) {
        [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_2_cars.jpg" WithThresholdMin:minValue AndThresholdMax:maxValue];
    } else if(lastButtonPressed == mode6Button) {
        [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_4_cars.jpg" WithThresholdMin:minValue AndThresholdMax:maxValue];
    }
}
- (IBAction)maxSliderValueChanged:(id)sender {
    NSSlider *slider = (NSSlider *)sender;
    maxValue = slider.intValue;
    [maxValueTextField setStringValue:[NSString stringWithFormat:@"%d", maxValue]];
    if(lastButtonPressed == mode4Button) {
        [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_template.jpg" WithThresholdMin:minValue AndThresholdMax:maxValue];
    } else if(lastButtonPressed == mode5Button) {
        [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_2_cars.jpg" WithThresholdMin:minValue AndThresholdMax:maxValue];
    } else if(lastButtonPressed == mode6Button) {
        [imageProcessor processImageOnObjectDetectionMethodWithFileName:@"AVS_ParkingLot.app/Contents/Resources/parkingLot_4_cars.jpg" WithThresholdMin:minValue AndThresholdMax:maxValue];
    }
}

@end

