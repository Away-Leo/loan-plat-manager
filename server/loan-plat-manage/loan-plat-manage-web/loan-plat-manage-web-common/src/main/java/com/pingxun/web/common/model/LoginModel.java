package com.pingxun.web.common.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 */
public class LoginModel implements Serializable {
    public static interface PasswordLogin {

    }

    public static interface PhoneLogin {

    }

    @NotEmpty
    private String userName;

    @NotEmpty(groups = PasswordLogin.class)
    private String password;

    @NotNull
    private Long merchantId=1L;

    @NotNull
    private String type="manager";

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
