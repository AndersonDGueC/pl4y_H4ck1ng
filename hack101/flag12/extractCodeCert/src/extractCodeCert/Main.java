package extractCodeCert;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;


public class Main {
	
	public static void main(String[] args) {
			String filepath = "/home/adgc/Desktop/certificate_burp1.der";
			File file = new File(filepath);
			System.out.println(extract(file));
	}

	
public static String extract(File certificate){
	
		FileInputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(certificate);
			X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X509")
					.generateCertificate(inputStream);
			
			byte[] publicKeyEncoded = x509Certificate.getPublicKey().getEncoded();
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] publicKeySha256 = messageDigest.digest(publicKeyEncoded);
			byte[] publicKeyShaBase64 =  Base64.getEncoder().encode(publicKeySha256);
			String localVar = new String(publicKeyShaBase64);
			
			//System.out.println(localVar);
			
			return new String(publicKeyShaBase64);
		//return localVar;
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
			}
		
		return "";
		
		}
         
}


