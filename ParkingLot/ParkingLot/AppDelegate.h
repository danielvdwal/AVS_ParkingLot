//
//  AppDelegate.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageCapturerWindow.h"
#import "ClusterManagerWindow.h"

@interface AppDelegate : NSObject <NSApplicationDelegate, ImageCapturedDelegate> {
    @private
    ImageCapturerWindow *_imageCapturerWindow;
    ClusterManagerWindow *_clusterManagerWindow;
}

@property (assign) IBOutlet NSWindow *window;
@property (strong) IBOutlet NSImageCell *camView;

- (IBAction)showImageCapturer:(id)sender;
- (IBAction)showClusterManagerWindow:(id)sender;
- (IBAction)start:(id)sender;

@end
