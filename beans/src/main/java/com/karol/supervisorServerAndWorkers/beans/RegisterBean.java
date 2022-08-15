package com.karol.supervisorServerAndWorkers.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterBean {

    private String address;
    private String verificationCode;

}
