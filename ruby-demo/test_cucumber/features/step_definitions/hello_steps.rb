require "rubydemo/hello"

@hello

Given /^I have created a Hello object$/ do
  @hello = RubyDemo::Hello.new
end

When /^I ask it to speak$/ do
  @hello.speak
end
Then /^It should return "([^\"]*)"$/ do |returnValue|
  
end