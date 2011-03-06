#!usr/bin/bash

SINATRA_GEM=$(gem list --local | grep sinatra)

echo "Sinatra Gem : $SINATRA_GEM"

