package sit707_week4;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;


public class LoginFormTest {

	private final String CORRECT_PASSWORD = "shreya_pass";
	private final String CORRECT_USERNAME = "shreya";
	private final String CORRECT_VALIDATION_CODE = "222222";
	private final String EMPTY_USERNAME = "Empty Username";
	private final String EMPTY_PASSWORD = "Empty Password";
	private final String CREDENTIAL_MISMATCH = "Credential mismatch";

	private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final Random random = new Random();

	public String generateRandomString(int length) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
			stringBuilder.append(ALPHA_NUMERIC_STRING.charAt(index));
		}

		return stringBuilder.toString();
	}

	@Test
	public void testStudentIdentity() {
		String studentId = "224114235";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "shreya";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, null);
		Assert.assertEquals(EMPTY_USERNAME, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailEmptyUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, CORRECT_PASSWORD);
		Assert.assertEquals(EMPTY_USERNAME, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailCorrectUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(CORRECT_USERNAME, null);
		Assert.assertEquals(EMPTY_PASSWORD, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailEmptyUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, generateRandomString(9));
		Assert.assertEquals(EMPTY_USERNAME, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailWrongUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(generateRandomString(9), null);
		Assert.assertEquals(EMPTY_PASSWORD, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailCorrectUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(CORRECT_USERNAME, generateRandomString(9));
		Assert.assertEquals(CREDENTIAL_MISMATCH, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailWrongUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(generateRandomString(9), CORRECT_PASSWORD);
		Assert.assertEquals(CREDENTIAL_MISMATCH, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailWrongUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(generateRandomString(9), generateRandomString(9));
		Assert.assertEquals(CREDENTIAL_MISMATCH, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
	}

	@Test
	public void testFailCorrectUsernameAndCorrectPasswordAndNullValCode() {
		LoginStatus status = LoginForm.login(CORRECT_USERNAME, CORRECT_PASSWORD);
		Assert.assertEquals(CORRECT_VALIDATION_CODE, status.getErrorMsg());
		Assert.assertTrue(status.isLoginSuccess());

	}
	
	@Test
	public void testPassCorrectUsernameAndCorrectPasswordAndCorrectValCode() {
		LoginStatus status = LoginForm.login(CORRECT_USERNAME, CORRECT_PASSWORD);
		Assert.assertEquals(CORRECT_VALIDATION_CODE, status.getErrorMsg());
		Assert.assertTrue(status.isLoginSuccess());
		Assert.assertTrue(LoginForm.validateCode(CORRECT_VALIDATION_CODE));

	}

	@Test
	public void testFailWrongUsernameAndCorrectPasswordAndWrongValCode() {
		LoginStatus status = LoginForm.login(generateRandomString(9), CORRECT_PASSWORD);
		Assert.assertEquals(CREDENTIAL_MISMATCH, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
		Assert.assertFalse(LoginForm.validateCode(CREDENTIAL_MISMATCH));

	}
	
	@Test
	public void testFailCorrectUsernameAndWrongPasswordAndWrongValCode() {
		LoginStatus status = LoginForm.login(CORRECT_USERNAME, generateRandomString(9));
		Assert.assertEquals(CREDENTIAL_MISMATCH, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
		Assert.assertFalse(LoginForm.validateCode(CREDENTIAL_MISMATCH));

	}
	
	@Test
	public void testFailWrongUsernameAndWrongPasswordAndWrongValCode() {
		LoginStatus status = LoginForm.login(generateRandomString(9), generateRandomString(9));
		Assert.assertEquals(CREDENTIAL_MISMATCH, status.getErrorMsg());
		Assert.assertFalse(status.isLoginSuccess());
		Assert.assertFalse(LoginForm.validateCode(CREDENTIAL_MISMATCH));

	}
	
	@Test
	public void testNullValCode() {
		Assert.assertFalse(LoginForm.validateCode(null));
	}
	
	@Test
	public void testWrongValCode() {
		Assert.assertFalse(LoginForm.validateCode(generateRandomString(6)));
	}
	
	@Test
	public void testCorrectValCode() {
		Assert.assertTrue(LoginForm.validateCode(CORRECT_VALIDATION_CODE));
	}

}
