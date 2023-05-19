package com.revise.mail_sender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailData {

    private String recipient;
    private String msgBody;
    private String subject;
}
