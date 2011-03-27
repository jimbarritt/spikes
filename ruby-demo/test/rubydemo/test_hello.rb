require "test/unit"
require "rubydemo/hello"

class TC_HelloTest < Test::Unit::TestCase

  def setup
    @hello = RubyDemo::Hello.new
  end

  def test_speak
    assert @hello.speak, "Speak should be true"
  end

  def test_says_hello_to_jim    
    result = @hello.speak_to "Jim"
    assert_equal "Hello Jim", result
  end

  def test_says_hello_to_john
    result = @hello.speak_to "John"
    assert_equal "Hello John", result
  end

  def test_remembers_how_many_times_it_was_called
    @hello.speak_to "Bill"
    @hello.speak_to "Fred"
    @hello.speak_to "Phil"

    count = @hello.numberOfTimesCalled

    assert_equal 3,  count
    
    @hello.speak_to "Jobby"
  end
end


