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
@property (assign) IBOutlet NSImageView *cameraView;
@property (strong) IBOutlet NSButton *mode1Button;
@property (strong) IBOutlet NSButton *mode2Button;
@property (strong) IBOutlet NSButton *mode3Button;
@property (assign) IBOutlet NSButton *startButton;
@property (assign) IBOutlet NSButton *stopButton;
@property (strong) IBOutlet NSTextView *textView;
@property (strong) ImageProcessor *imageProcessor;
@property (strong) NSArray *imageViews;

- (IBAction)mode1Clicked:(id)sender;
- (IBAction)mode2Clicked:(id)sender;
- (IBAction)mode3Clicked:(id)sender;
- (IBAction)startClicked:(id)sender;
- (IBAction)stopClicked:(id)sender;

@end
