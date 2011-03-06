#!/bin/bash

SINATRA_GEM=$(gem list --local | grep sinatra)

if [ "$SINATRA_GEM" != "" ]; then
	echo "Sinatra Gem : [$SINATRA_GEM]"
else	
	echo "Going to install sinatra gem..."	
	gem install sinatra
fi

echo "Running hi.rb now"
ruby -rubygems hi.rb
