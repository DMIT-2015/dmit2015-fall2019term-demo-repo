package dmit2015.encryption;

import javax.swing.JOptionPane;

import org.jasypt.util.text.AES256TextEncryptor;

public class EncryptText {

	public static void main(String[] args) {
//		System.out.println("Enter a text value to encrypt: ");
		String plainText = JOptionPane.showInputDialog("Enter text value to encrypt");
		AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword("Password2015");
		String myEncryptedText = textEncryptor.encrypt(plainText);
		String message = String.format("Encypted text value: %s",myEncryptedText);
		System.out.println(message);
		JOptionPane.showMessageDialog(null, message);
		
		String cipherText = JOptionPane.showInputDialog("Enter value to decrypt:");				
		String plainTextValue = textEncryptor.decrypt(cipherText);
		String plainTextValueMesage = String.format("Plain Text value is: %s", plainTextValue);
		System.out.print(plainTextValueMesage);
		JOptionPane.showMessageDialog(null, plainTextValueMesage);

	}

}
