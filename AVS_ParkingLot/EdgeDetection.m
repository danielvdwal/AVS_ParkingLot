//
//  EdgeDetection.m
//  AVS_ParkingLot
//
//  Created by Robert Scherbarth on 15.04.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "EdgeDetection.h"


@implementation EdgeDetection

/** General variables */
CvSeq* lines = 0;

int i;


-(IplImage*)probabilisticHough:(IplImage*)src{
    CvMemStorage* storage = cvCreateMemStorage(0);
    
    IplImage *probabilistic_hough = [self canny:src];
    
    // Use Probabilistic Hough Transform
    lines = cvHoughLines2( probabilistic_hough, storage, CV_HOUGH_PROBABILISTIC, 1, CV_PI/180, 100, 500, 0 );
    
    // Show the result
    for( i = 0; i < lines->total; i++ )
    {
        CvPoint* line = (CvPoint*)cvGetSeqElem(lines,i);
        cvLine( probabilistic_hough, line[0], line[1], CV_RGB(255,0,0), 3, CV_AA, 0 );
    }
    
    return probabilistic_hough;
}

-(IplImage*)canny:(IplImage*)src {
    //IplImage *src_gray = cvCreateImage(cvSize(src->width, src->height), src->depth, src->nChannels);
    IplImage *edges = cvCreateImage(cvSize(src->width, src->height), src->depth, src->nChannels);
    
    // Apply Canny edge detector
    cvCanny( src, edges, 50, 200, 3 );
    
    // Image into Gray
    //cvCvtColor( edges, src_gray, CV_GRAY2RGBA );

    return edges;
}


@end
