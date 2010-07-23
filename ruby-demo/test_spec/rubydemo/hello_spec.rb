require "spec"
require "RubyDemo/hello"


describe RubyDemo::Hello do

  # Called before each example.
  before(:each) do
    # Do nothing
  end

  # Called after each example.
  after(:each) do
    # Do nothing
  end

  it "should speak" do    
    hello = RubyDemo::Hello.new
    hello.speak.should == true
  end
end

