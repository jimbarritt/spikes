//
//  URLHandlerCommand.m
//  URLHandler
//
//  Created by Kimbro  Staken on Tue Dec 16 2003.
//  Copyright (c) 2003 __MyCompanyName__. All rights reserved.
//

#import "URLHandlerCommand.h"


@implementation URLHandlerCommand

- (id)performDefaultImplementation {
    NSString *urlString = [self directParameter];
    
    NSLog(@"New url = %@", urlString);
	
	NSTask *task;
    task = [[NSTask alloc] init];
    [task setLaunchPath: @"/usr/bin/browser-test"];
	
    NSArray *arguments;
    arguments = [NSArray arrayWithObjects: urlString, nil];
    [task setArguments: arguments];
	
    NSPipe *pipe;
    pipe = [NSPipe pipe];
    [task setStandardOutput: pipe];
	
    NSFileHandle *file;
    file = [pipe fileHandleForReading];
	
    [task launch];
    [task waitUntilExit];
	
    NSData *data;
    data = [file readDataToEndOfFile];
	
    NSString *string;
    string = [[NSString alloc] initWithData: data encoding: NSUTF8StringEncoding];
    NSLog (@"got\n%@", string);

    return nil;
}

@end
