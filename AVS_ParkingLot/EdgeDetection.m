//
//  EdgeDetection.m
//  AVS_ParkingLot
//
//  Created by Robert Scherbarth on 15.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#import "EdgeDetection.h"


@implementation EdgeDetection

/** General variables */

IplImage *src; 
IplImage *edges;
IplImage *src_gray;
IplImage *standard_hough, *probabilistic_hough;
CvSeq* lines = 0;

int i;


-(IplImage*)probabilisticHough:(IplImage*)src{
    CvMemStorage* storage = cvCreateMemStorage(0);
    
    
    probabilistic_hough = [self canny:src];
    
    /// 2. Use Probabilistic Hough Transform
    lines = cvHoughLines2( probabilistic_hough, storage, CV_HOUGH_PROBABILISTIC, 1, CV_PI/180, 50, 50, 10 );
    
    /// Show the result
    for( i = 0; i < lines->total; i++ )
    {
        CvPoint* line = (CvPoint*)cvGetSeqElem(lines,i);
        cvLine( probabilistic_hough, line[0], line[1], CV_RGB(255,0,0), 3, CV_AA, 0 );
    }
    return probabilistic_hough;
}

-(IplImage*)canny:(IplImage*)src {

    // Image into Gray
    cvCvtColor( src, src_gray, CV_GRAY2BGR );

    // Apply Canny edge detector
    cvCanny( src_gray, edges, 50, 200, 3 );

    
    return edges;
}


@end
