//
//  AppDelegate.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageCapturerWindow.h"
#import "ClusterManagerWindow.h"

@interface AppDelegate : NSObject <NSApplicationDelegate> {
    @private
    ImageCapturerWindow *_imageCapturerWindow;
    ClusterManagerWindow *_clusterManagerWindow;
}

@property (assign) IBOutlet NSWindow *window;

- (IBAction)showImageCapturer:(id)sender;
- (IBAction)showClusterManagerWindow:(id)sender;

@end
