//
//  ImageCapturerWindow.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageCapturerWindow.h"

@implementation ImageCapturerWindow

- (id)initWithWindow:(NSWindow *)window
{
    self = [super initWithWindow:window];
    if (self) {
        // Initialization code here.
        _imageCapturer = [[ImageCapturer alloc] init];
    }
    return self;
}

- (void)windowDidLoad
{
    [super windowDidLoad];
    
    // Implement this method to handle any initialization after your window controller's window has been loaded from its nib file.
    
    
    [[self startCameraButton] setEnabled:YES];
    [[self stopCameraButton] setEnabled:NO];
    [[self connectToClusterManagerButton] setEnabled:YES];
    [[self closeClusterManagerConnectionButton] setEnabled:NO];
}

- (void)windowWillClose:(NSNotification *)notification {
    [_imageCapturer stopCamera];
    
    [[self startCameraButton] setEnabled:YES];
    [[self stopCameraButton] setEnabled:NO];
    [[self connectToClusterManagerButton] setEnabled:YES];
    [[self closeClusterManagerConnectionButton] setEnabled:NO];
}

- (IBAction)startCamera:(id)sender {
    [[self startCameraButton] setEnabled:NO];
    [[self stopCameraButton] setEnabled:YES];
    
    [_imageCapturer startCamera];
}


- (IBAction)stopCamera:(id)sender {
    [_imageCapturer stopCamera];
    
    [[self startCameraButton] setEnabled:YES];
    [[self stopCameraButton] setEnabled:NO];
}


- (IBAction)connectToClusterManager:(id)sender {
    [[self connectToClusterManagerButton] setEnabled:NO];
    [[self closeClusterManagerConnectionButton] setEnabled:YES];
    
    [_imageCapturer connectToClusterManager];
}


- (IBAction)closeClusterManagerConnection:(id)sender {
    [_imageCapturer closeClusterMangerConnection];
    
    [[self connectToClusterManagerButton] setEnabled:YES];
    [[self closeClusterManagerConnectionButton] setEnabled:NO];
}

@end
