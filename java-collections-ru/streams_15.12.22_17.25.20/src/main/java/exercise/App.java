package exercise;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

// BEGIN
class App {

    public static Long getCountOfFreeEmails(List<String> emailsList) {
        return emailsList.stream()
                .filter(email -> StringUtils.isNoneBlank(email))
                .filter(email -> email.contains("@gmail.com")
                        || email.contains("@yandex.ru")
                        || email.contains("@hotmail.com"))
                .count();
    }
}
// END

