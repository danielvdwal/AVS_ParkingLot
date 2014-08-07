//
//  ClusterManagerConnector.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ClusterManagerConnector : NSObject {
    @private
    id _clusterManager;
    NSHost *_worker;
}

- (void)connectToClusterManager;
- (void)closeConnectionToClusterManager;
- (void)forwardImage:(cv::Mat)image;

@end
