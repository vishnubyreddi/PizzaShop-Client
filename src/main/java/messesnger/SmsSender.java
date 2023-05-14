package messesnger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Replace with your Twilio account SID
    private static final String ACCOUNT_SID = "your_account_sid_here";
    // Replace with your Twilio auth token
    private static final String AUTH_TOKEN = "your_auth_token_here";
    // Replace with your Twilio phone number
    private static final String TWILIO_PHONE_NUMBER = "your_twilio_phone_number_here";

    public static void sendSms(String toPhoneNumber, String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        messageText)
                .create();

        System.out.println("SMS message sent to " + toPhoneNumber + ": " + message.getSid());
    }
}