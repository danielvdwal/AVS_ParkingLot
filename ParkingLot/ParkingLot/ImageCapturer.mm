//
//  ImageCapturer.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageCapturer.h"
#import "NSImage_OpenCV.h"

@implementation ImageCapturer

- (id)init {
    if (self = [super init])
    {
        _cameraConnector = [[CameraConnector alloc] initWithDelegate:self];
        _clusterManagerConnector = [[ClusterManagerConnector alloc] init];
    }
    return self;
}

- (void)startCamera {
    [_cameraConnector connectToDefaultCamera];
    cv::namedWindow("Camera Image");
    [_cameraConnector startCapturing];
}

- (void)stopCamera {
    [_cameraConnector closeDefaultCameraConnection];
    cv::waitKey(100);
    cv::destroyWindow("Camera Image");
}

- (void)imageCaptured:(const cv::Mat&)image {
    //cv::Mat displayedImage;
    //cv::resize(image, displayedImage, cv::Size(960,720));
    //cv::imshow("Camera Image", displayedImage);
    //displayedImage.release();
    
    [_clusterManagerConnector forwardImage:image];
}

- (void)connectToClusterManager {
    [_clusterManagerConnector connectToClusterManager];
}

- (void)closeClusterMangerConnection {
    [_clusterManagerConnector closeConnectionToClusterManager];
}

@end
