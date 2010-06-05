require "test/unit"
require "RubyDemo/hello"

class TC_HelloTest < Test::Unit::TestCase

  def test_fail
    hello = RubyDemo::Hello.new
    assert(hello.speak, true)
  end
end


