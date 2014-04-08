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

@property (assign) IBOutlet NSWindow *window;
@property (assign) IBOutlet NSImageView *imageView;
@property (assign) IBOutlet NSButton *startButton;
@property (assign) IBOutlet NSButton *stopButton;
@property (assign) ImageProcessor *imageProcessor;

- (IBAction)startClicked:(id)sender;
- (IBAction)stopClicked:(id)sender;

@end
