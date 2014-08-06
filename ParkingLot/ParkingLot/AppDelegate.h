//
//  AppDelegate.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#include "CameraConnector.h"

@interface AppDelegate : NSObject <NSApplicationDelegate> {
    @private
    CameraConnector* _cameraConnector;
}

@property (assign) IBOutlet NSWindow *window;
@property (weak) IBOutlet NSImageView *cameraView;

- (IBAction)startCamera:(id)sender;
- (IBAction)stopCamera:(id)sender;
- (IBAction)startClusterManger:(id)sender;
- (IBAction)stopClusterManager:(id)sender;
- (IBAction)addClient:(id)sender;
- (IBAction)removeClient:(id)sender;

@end
