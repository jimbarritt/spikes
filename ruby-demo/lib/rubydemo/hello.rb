module RubyDemo

class Hello

  def initialize
    @count = 0
  end

  def speak
    return true
  end

  def speak_to name
    @count += 1
    return "Hello #{name}"
  end

  def numberOfTimesCalled
    return @count
  end

end

end 
  
