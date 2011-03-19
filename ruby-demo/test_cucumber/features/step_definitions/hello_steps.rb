require "rubydemo/hello"

@hello
@result = false

Given /^I have created a Hello object$/ do
  @hello = RubyDemo::Hello.new
end

When /^I ask it to speak$/ do
  @result = @hello.speak
end

Then /^It should return "([^\"]*)"$/ do |value|
  @result.should == object_to_boolean(value)
end

def object_to_boolean(value)
  return [true, "true", 1, "1", "T", "t"].include?(value.class == String ? value.downcase : value)
end
