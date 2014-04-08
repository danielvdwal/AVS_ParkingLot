//
//  AppDelegate.m
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import "AppDelegate.h"

@implementation AppDelegate

@synthesize window;
@synthesize imageView;
@synthesize startButton;
@synthesize stopButton;
@synthesize imageProcessor;

- (id)init {
    self = [super init];
    if(self) {
        imageProcessor = [[ImageProcessor alloc] init];
    }
    return self;
}

- (void)dealloc
{
    [super dealloc];
}

- (void)applicationDidFinishLaunching:(NSNotification *)notification
{
}

- (void)showImage:(NSImage *)image {
    imageView.image = image;
}

- (IBAction)startClicked:(id)sender {
    if([imageProcessor startWithDelegate:self]) {
        [startButton setEnabled:NO];
        [stopButton setEnabled:YES];
    }
}

- (IBAction)stopClicked:(id)sender {
    [imageProcessor stop];
    [stopButton setEnabled:NO];
    [startButton setEnabled:YES];
}

@end
