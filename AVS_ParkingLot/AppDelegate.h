//
//  AppDelegate.h
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import "ImageProcessor.h"

@interface AppDelegate : NSObject <NSApplicationDelegate, ImageShower>

@property (strong) IBOutlet NSWindow *window;
@property (strong) IBOutlet NSImageView *image1View;
@property (strong) IBOutlet NSImageView *image2View;
@property (strong) IBOutlet NSImageView *image3View;
@property (strong) IBOutlet NSImageView *image4View;
@property (strong) IBOutlet NSImageView *cameraView;
@property (strong) IBOutlet NSButton *mode1Button;
@property (strong) IBOutlet NSButton *mode2Button;
@property (strong) IBOutlet NSButton *mode3Button;
@property (strong) IBOutlet NSButton *mode4Button;
@property (strong) IBOutlet NSButton *mode5Button;
@property (strong) IBOutlet NSButton *mode6Button;
@property (strong) IBOutlet NSButton *startButton;
@property (strong) IBOutlet NSButton *stopButton;
@property (strong) IBOutlet NSTextView *textView;
@property (strong) ImageProcessor *imageProcessor;
@property (strong) NSArray *imageViews;
@property (strong) NSButton *lastButtonPressed;
@property (strong) IBOutlet NSTextField *minValueTextField;
@property (strong) IBOutlet NSTextField *maxValueTextField;
@property (assign) int minValue;
@property (assign) int maxValue;

- (IBAction)mode1Clicked:(id)sender;
- (IBAction)mode2Clicked:(id)sender;
- (IBAction)mode3Clicked:(id)sender;
- (IBAction)mode4Clicked:(id)sender;
- (IBAction)mode5Clicked:(id)sender;
- (IBAction)mode6Clicked:(id)sender;
- (IBAction)startClicked:(id)sender;
- (IBAction)stopClicked:(id)sender;
- (IBAction)minSliderValueChanged:(id)sender;
- (IBAction)maxSliderValueChanged:(id)sender;

@end
