require "rubydemo/hello"

@hello
@result = false

Given /^I have created a Hello object$/ do
  @hello = RubyDemo::Hello.new
end

When /^I ask it to speak$/ do
  @result = @hello.speak
end
Then /^It should return "([^\"]*)"$/ do |returnValue|
  @result.should == true  
end