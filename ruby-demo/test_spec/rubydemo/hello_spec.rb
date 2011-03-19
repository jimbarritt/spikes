require "rspec"
require "RubyDemo/hello"

describe RubyDemo::Hello do

  # Called before each example.
  before(:each) do
  end

  # Called after each example.
  after(:each) do
  end

  it "should speak" do    
    hello = RubyDemo::Hello.new
    hello.speak.should == true
  end

  it "should say hello" do
    hello = RubyDemo::Hello.new
    result = hello.speakTo "Jim"
    result.should == "Hello Jim"
  end
end

