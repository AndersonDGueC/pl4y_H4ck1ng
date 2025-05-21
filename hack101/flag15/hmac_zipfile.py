import hmac
import binascii


#decode private key from hex to plain text

key = binascii.unhexlify("8c34bac50d9b096d41cafb53683b315690acf65a11b5f63250c61f7718fa1d1d")

#create hmac object, default digest mode is MD5
digest_maker = hmac.new(key)

f = open('bookmarks-tor.zip', rb)
try:
	while True:
		block = f.read(1024)
		if not block:
			break
		# updates the HMAC object with the message.
		digest_maker.update(block)
finally:
	f.close()
#hmac signature

digest = digest_maker.hexdigest()
print(digest)
