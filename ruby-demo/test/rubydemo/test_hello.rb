require "test/unit"
require "rubydemo/hello"

class TC_HelloTest < Test::Unit::TestCase

  def test_speak
    hello = RubyDemo::Hello.new
    assert hello.speak, "Speak should be true"
  end

  def test_says_hello_to_jim
    hello = RubyDemo::Hello.new
    result = hello.speakTo "Jim"
    assert_equal "Hello Jim", result
  end

  def test_says_hello_to_john
    hello = RubyDemo::Hello.new
    result = hello.speakTo "John"
    assert_equal "Hello John", result
  end
end


