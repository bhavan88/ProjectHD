import java.util.regex.Pattern;

public class MaskEmailOrPhoneNumberUtil {

public static final char MASK_CHAR = '*';

    public static void main(String[] args) throws Exception {

        // Please enter valid email or phone number in below string as demo purpose
        // If we datas come from database than we can make call and have the database connetion here 
        // Where Str will be removed and replaced. So which will read the input. 
        
        String str = "bhavan.bpt@gmail.com";


        String result = getResult(str);

        System.out.println("Result of inputed String is :-" + result);

    }

    public static String getResult(String str)  {

        String outputString = null;
        if(isValidEmailOrPhoneNumber(str)) {

            boolean isValidEmail = false;
            boolean isValidPhoneNumber = false;

            if(isValidEmailAddress(str)) isValidEmail = true;

            if(isValidPhoneNumber(str)) isValidPhoneNumber = true;

            if(isValidEmail || isValidPhoneNumber) {

                if(isValidEmail) {
                    String maskedEmailAddress = maskEmailAddress(str, MASK_CHAR);

                    System.out.println("Mask Email Address:- " + maskedEmailAddress);

                    return maskedEmailAddress;

                } else if(isValidPhoneNumber) {

                    // mask first 4 characters
                    String phoneNumber = maskString(str, 3, 6, MASK_CHAR);

                    System.out.println("Mask Phone Number:-" + phoneNumber);

                    return phoneNumber;
                }

            } else {
                System.out.println("Email address and Phone Number are not valid..");
            }

        } else {
            System.out.println("Email Or Phone number is blank..");
        }

        return outputString;

    }

    public static String maskEmailAddress(String emailString, char maskChar)  {

        String[] emailParts = emailString.split("@");

        // mask first part
        String firstPartWithMasking = "";
        if(emailParts[0].length() < 4) firstPartWithMasking = maskString(emailParts[0], 0, emailParts[0].length(), '*');
        else firstPartWithMasking = maskString(emailParts[0], 1, emailParts[0].length() - 1, '*');

        // now append the domain part to the masked id part
        return firstPartWithMasking + "@" + emailParts[1];
    }

    public static String maskString(String strText, int start, int end, char maskChar)  {

        if(start < 0) start = 0;

        if(end > strText.length()) end = strText.length();

        int maskLength = end - start;

        if(maskLength == 0) return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for(int i = 0; i < maskLength; i++) {
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
    }

    public static boolean isValidEmailOrPhoneNumber(String emailOrPhoneNumberStr) {
        if(emailOrPhoneNumberStr == null || emailOrPhoneNumberStr.trim().equals("")) {
            return false;
        }
        return true;
    }

    /***
     * Assuming phone number is not contain "+" sing and country code. and Phone number length is 10
     * 
     * @param emailOrPhoneNumberStr
     * @return
     */
    public static boolean isValidPhoneNumber(String emailOrPhoneNumberStr) {
        if(emailOrPhoneNumberStr.matches("[0-9]+") && emailOrPhoneNumberStr.length() == 10) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmailAddress(String emailOrPhoneNumberStr) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(emailOrPhoneNumberStr).matches();
    }
}
