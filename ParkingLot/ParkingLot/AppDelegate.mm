//
//  AppDelegate.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "AppDelegate.h"
#import "NSImage_OpenCV.h"

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

- (IBAction)start:(id)sender {
    CameraConnector *cam = [[CameraConnector alloc] initWithDelegate:self];
    [cam connectToDefaultCamera];
    [cam startCapturing];
}

- (void)imageCaptured:(cv::Mat)image {
    NSImage *img = [NSImage imageWithCVMat:image];
    [[self camView] setImage:img];
    image.release();
}

@end
