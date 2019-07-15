package services.interfaces;

import entity.Code;

public interface MailService {

    void sendMessage(Code code, String email);

}
