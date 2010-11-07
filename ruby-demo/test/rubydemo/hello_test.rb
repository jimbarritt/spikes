require "test/unit"
require "rubydemo/hello"

class TC_HelloTest < Test::Unit::TestCase

  def test_speak
    hello = RubyDemo::Hello.new
    assert(hello.speak, "Speak should be true")
  end
end


