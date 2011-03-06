#!/usr/bin/ruby

def colorize(text, color_code)
  "#{color_code}#{text}\033[0m"
end

def red(text); colorize(text, "\033[31m"); end
def green(text); colorize(text, "\033[32m"); end

def is_installed?(gemName) 
  gemName = %x(gem list --local | grep #{gemName})
  gemName != ""  
end

def install_cucumber
  system "sudo gem install gherkin"
  system "sudo gem install cucumber"
end

def install_ZenTest
  system "sudo gem install hoe"
  system "sudo gem install ZenTest"
  system "sudo gem install redgreen"  
end

def install_rspec
  system "sudo gem install rspec"
end

def install(gemName) 
  if is_installed? gemName
    puts green "\n#{gemName} is already installed, carry on!\n"  
  else
    puts green "\nInstalling #{gemName}...\n"
    send "install_#{gemName}"
  end
end


install "cucumber"
install "ZenTest"
install "rspec"

