//
//  MessageProtocol.h
//  AVS_ParkingLot
//
//  Created by Robert Scherbarth on 05.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//
//  Is the protocol to send messages from server to client.

#import <Foundation/Foundation.h>

@protocol MessageProtocol <NSObject>

- (void)message;

- (void)addClient;
- (BOOL)removeClient;

@end
