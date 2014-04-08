//
//  ImageShower.h
//  AVS_ParkingLot
//
//  Created by Daniel van der Wal on 08.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol ImageShower <NSObject>

- (void)setText:(NSString*)text;
- (void)showCameraImage:(NSImage*)image;
- (void)showImages:(NSArray*)images;

@end
