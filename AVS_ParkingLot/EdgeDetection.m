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
    
    @autoreleasepool {
        CvMemStorage* storage = cvCreateMemStorage(0);
        
        IplImage *edges = cvCreateImage(cvSize(src->width, src->height), src->depth, 3);
        IplImage *cannyImg = cvCreateImage(cvSize(src->width, src->height), src->depth, src->nChannels);

        // Apply Canny edge detector
        cvCanny( src, cannyImg, 50, 200, 3 );
    
        // Image into Gray
        cvCvtColor( cannyImg, edges, CV_GRAY2BGR );
    
        // Use Probabilistic Hough Transform
        lines = cvHoughLines2( cannyImg, storage, CV_HOUGH_PROBABILISTIC, 1, CV_PI/180, 50, 150, 25 );
    
        // Show the result
        for( i = 0; i < lines->total; i++ )
        {
            CvPoint* line = (CvPoint*)cvGetSeqElem(lines,i);
            cvLine( edges, line[0], line[1], CV_RGB(255,0,0), 10, CV_AA, 0 );
        }
        
        return edges;
    }
}

@end
