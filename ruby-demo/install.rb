#!/usr/bin/ruby

def colorize(text, color_code)
  "#{color_code}#{text}\033[0m"
end

def red(text); colorize(text, "\033[31m"); end
def green(text); colorize(text, "\033[32m"); end

def install
  puts green("\nHi, I am going to install everything that this app needs for you, above and beyond ruby\n")

  system("sudo gem install gherkin")
  system("sudo gem install cucumber")
end

cucumberGemName = %x(gem list --local | grep cucumber)

if cucumberGemName == ""
  install
else
  puts green("\nCucmber is already installed, carry on!\n")
end

