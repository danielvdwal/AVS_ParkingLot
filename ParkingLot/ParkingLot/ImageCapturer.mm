//
//  ImageCapturer.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageCapturer.h"

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
    cv::namedWindow("Camera Image", CV_WINDOW_NORMAL);
    [_cameraConnector startCapturing];
}

- (void)stopCamera {
    [_cameraConnector closeDefaultCameraConnection];
    cv::waitKey(100);
    cv::destroyWindow("Camera Image");
}

- (void)imageCaptured:(cv::Mat)image {
    cv::Mat displayedImage;
    [_clusterManagerConnector forwardImage:image.clone()];
    
    cv::resize(image, displayedImage, cv::Size(960,720));
    cv::imshow("Camera Image", displayedImage);
    displayedImage.release();
}

- (void)connectToClusterManager {
    [_clusterManagerConnector connectToClusterManager];
}

- (void)closeClusterMangerConnection {
    [_clusterManagerConnector closeConnectionToClusterManager];
}

@end