//
//  CameraConnector.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <opencv2/opencv.hpp>

@interface CameraConnector : NSObject {
    @private
    cv::VideoCapture _camera;
    BOOL _connected;
    //CvCapture* _camera;
    //BOOL _connected;
    //CvMat _frame;
    //CvMat _frameCopy;
    //CvMat _image;
}

- (void)connectToCameraOpenCV:(int)camId;
- (void)connectToCamera:(int)camId
         AndSendImageTo:(NSImageView*)view;
- (void)closeCameraConnection:(int)camId;

@end
