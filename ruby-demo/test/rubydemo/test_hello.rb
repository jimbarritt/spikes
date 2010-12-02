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
    result = @hello.speakTo "Jim"
    assert_equal "Hello Jim", result
  end

  def test_says_hello_to_john
    result = @hello.speakTo "John"
    assert_equal "Hello John", result
  end

  def test_remembers_how_many_times_it_was_called
    @hello.speakTo "Bill"
    @hello.speakTo "Fred"
    @hello.speakTo "Phil"

    count = @hello.numberOfTimesCalled

    assert_equal 3,  count
    
    @hello.speakTo "Jobby"
  end
end


