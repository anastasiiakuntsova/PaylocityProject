package Helpers;

import net.bytebuddy.utility.RandomString;

public class StringHelper {

    public static String getRandomEmail() {
        RandomString randomString = new RandomString(15);
        RandomString randomString2 = new RandomString(5);

        String email = randomString.nextString() + "@" + randomString2.nextString() + ".com";
        return email;
    }

    public static String getRandomPassword() {
        RandomString randomString = new RandomString(15);
        String password = randomString.nextString();

        return password;

    }
}
