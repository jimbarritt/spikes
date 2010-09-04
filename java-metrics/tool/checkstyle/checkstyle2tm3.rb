require 'rubygems'
require 'nokogiri'

# Initial version provided by Erik Doernenburg (http://erik.doernenburg.com/2010/05/metrics-tree-maps/)
class Metrics
  
  PATH_SEP = /\//                  
    
  def initialize(name)
      @name = name
      @flength = 0
      @cdac = 0
      @cfoc = 0
      @mcount = 0
      @wmc_len = 0
      @wmc_cc = 0
  end

  def add(source, message)
    case source.split(/\./).last
      when "FileLengthCheck" 
        @flength = getval(message, 3)
      when "ClassDataAbstractionCouplingCheck" 
        @cdac = getval(message, 5)
      when "ClassFanOutComplexityCheck" 
        @cfoc = getval(message, 4)
      when "MethodLengthCheck" 
     	  @wmc_len += getval(message, 3) 
      when "CyclomaticComplexityCheck" 
        @wmc_cc += getval(message, 3)
        @mcount += 1
    end
  end
     
  def getval(message, pos)
	  message.split(/ /)[pos].sub(/,/, "").to_i
  end

  def self.titles
    "FLENGTH\tCDAC\tCFOC\tMCOUNT\tWMCloc\tWMCcc"       
  end       
  
  def self.types
    "INTEGER\tINTEGER\tINTEGER\tINTEGER\tINTEGER\tINTEGER"
  end
  
  def to_s    
    path = @name.chomp(".java").split(PATH_SEP).join("\t") 
    return "#{@flength}\t#{@cdac}\t#{@cfoc}\t#{@mcount}\t#{@wmc_len}\t#{@wmc_cc}\t#{path}"
  end
  
end


class Processor

  def initialize(filename, prefix, testPrefix)
    @doc = Nokogiri::XML(File.new(filename))
    @prefix = prefix
    @test_prefix = testPrefix
  end

  def run
    puts Metrics.titles
    puts Metrics.types
    @doc.xpath("//file").each do |filenode|    
      metrics = Metrics.new(filenode["name"].sub(@prefix, "").sub(@test_prefix, ""))      
      filenode.xpath(".//error").each do |errnode| 
        metrics.add(errnode["source"], errnode["message"])
      end  
      puts metrics.to_s
    end
  end
  
end

Processor.new(ARGV[0], ARGV[1], ARGV[2]).run
