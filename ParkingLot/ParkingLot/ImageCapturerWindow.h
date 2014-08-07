//
//  ImageCapturerWindow.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ImageCapturer.h"

@interface ImageCapturerWindow : NSWindowController<NSWindowDelegate> {
    @private
    ImageCapturer *_imageCapturer;
}

@property (strong) IBOutlet NSButton *startCameraButton;
@property (strong) IBOutlet NSButton *stopCameraButton;
@property (strong) IBOutlet NSButton *connectToClusterManagerButton;
@property (strong) IBOutlet NSButton *closeClusterManagerConnectionButton;

- (IBAction)startCamera:(id)sender;
- (IBAction)stopCamera:(id)sender;
- (IBAction)connectToClusterManager:(id)sender;
- (IBAction)closeClusterManagerConnection:(id)sender;

@end
