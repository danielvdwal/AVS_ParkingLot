//
//  AppDelegate.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "AppDelegate.h"

@implementation AppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    // Insert code here to initialize your application
}

- (IBAction)showImageCapturer:(id)sender {
    
    if(!_imageCapturerWindow) {
        _imageCapturerWindow = [[ImageCapturerWindow alloc] initWithWindowNibName:@"ImageCapturerWindow"];
    }
    [_imageCapturerWindow showWindow:self];
}

- (IBAction)showClusterManagerWindow:(id)sender {
   
    if(!_clusterManagerWindow){
        
        _clusterManagerWindow = [[ClusterManagerWindow alloc] initWithWindowNibName:@"ClusterManagerWindow"];
        
    }
    [_clusterManagerWindow showWindow:self];    
}

@end
