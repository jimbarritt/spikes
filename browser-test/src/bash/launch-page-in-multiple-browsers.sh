#!/bin/bash

TEST_THIS_URL=$1
REAL_URL=$(echo $TEST_THIS_URL | sed 's/test-this/http/g')
echo "Launching [$REAL_URL] in multiple browsers"

open -a /Applications/Google\ Chrome.app/ $REAL_URL
open -a /Applications/Safari.app/ $REAL_URL
open -a /Applications/Firefox.app/ $REAL_URL