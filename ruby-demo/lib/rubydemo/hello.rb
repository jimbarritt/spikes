module RubyDemo


class Hello

  def initialize
    @count = 0
  end

  def speak
    return true
  end

  def speakTo name
    @count = @count += 1
    return "Hello #{name}"
  end

  def numberOfTimesCalled
    return @count
  end

end

end 
  
