//
//  AppDelegate.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#include "CameraConnector.h"
#include "ClusterManagerWindow.h"

@interface AppDelegate : NSObject <NSApplicationDelegate> {
    @private
    CameraConnector *_cameraConnector;
    ClusterManagerWindow *_clusterManagerWindow;
}

@property (assign) IBOutlet NSWindow *window;
@property (strong) IBOutlet NSImageView *cameraView;

- (IBAction)startCamera:(id)sender;
- (IBAction)stopCamera:(id)sender;
- (IBAction)showClusterManagerWindow:(id)sender;
- (void)setImage:(NSImage *)image;

@end
