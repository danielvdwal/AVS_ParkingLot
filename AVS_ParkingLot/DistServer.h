//
//  DistServer.h
//  AVS_ParkingLot
//
//  Created by Robert Scherbarth on 05.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DistServer : NSObject

{
    int      *_numberOfClients;
}

-(void)startServer;
-(void)stopServer;

@end
