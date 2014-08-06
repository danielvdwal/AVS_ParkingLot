//
//  MessageProtocol.h
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol MessageProtocol <NSObject>

- (void)doSomething;

- (void)addClient;
- (BOOL)removeClient;

// Methods for ImageProcessorWorker
- (void)addImageProcessorWorker:(NSHost*)worker;
- (BOOL)removeImageProcessorWorker:(NSHost*)worker;

@end
