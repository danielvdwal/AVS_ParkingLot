//
//  AppDelegate.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface AppDelegate : NSObject <NSApplicationDelegate>

@property (assign) IBOutlet NSWindow *window;
@property (weak) IBOutlet NSImageView *cameraView;

- (IBAction)startCamera:(id)sender;
- (IBAction)stopCamera:(id)sender;
- (IBAction)startClusterManger:(id)sender;
- (IBAction)stopClusterManager:(id)sender;
- (IBAction)addClient:(id)sender;
- (IBAction)removeClient:(id)sender;

@end
