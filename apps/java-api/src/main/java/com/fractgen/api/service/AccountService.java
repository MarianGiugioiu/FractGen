package com.fractgen.api.service;

import com.fractgen.api.dto.AccountDTO;
import com.fractgen.api.dto.AccountName;
import com.fractgen.api.dto.Email;
import com.fractgen.api.dto.NewPassword;
import com.fractgen.api.exception.EmailNotVerifiedException;
import com.fractgen.api.exception.ResourceIncompatibleException;
import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Account;
import com.fractgen.api.repo.AccountRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
  @Autowired
  AccountRepo accountRepo;

  @Autowired
  PasswordService passwordService;

  @Autowired
  private JavaMailSender mailSender;

  public Account register(AccountName accountName, String siteURL)
    throws UnsupportedEncodingException, MessagingException {
    Account account = new Account();
    account.setEmail(accountName.getAccount().getEmail());
    account.setPassword(accountName.getAccount().getPassword());
    account.setProfile(null);
    String encodedPassword = passwordService.encodePassword(account.getPassword());
    account.setPassword(encodedPassword);

    String randomCode = RandomString.make(64);
    account.setVerificationCode(randomCode);
    account.setEnabled(false);

    accountName.setAccount(account);

    accountRepo.save(account);

    sendVerificationEmail(accountName, siteURL);

    return account;
  }

  public String ProcessPasswordReset(Email email, String siteURL)
    throws ResourceNotFoundException, UnsupportedEncodingException, MessagingException {
    Account account = accountRepo.findByEmail(email.getEmail()).orElseThrow(ResourceNotFoundException::new);
    String randomCode = RandomString.make(64);
    account.setVerificationCode(randomCode);
    accountRepo.save(account);
    AccountName accountName = new AccountName(account, account.getProfile().getName());

    sendResetPasswordEmail(accountName, siteURL);

    return "Mail successfully sent";
  }

  private void sendVerificationEmail(AccountName accountName, String siteURL)
    throws MessagingException, UnsupportedEncodingException {
    Account account = accountName.getAccount();
    String name = accountName.getName();
    String toAddress = account.getEmail();
    String fromAddress = "fractget.world@gmail.com";
    String senderName = "Fractal World";
    String subject = "Please verify your registration";
    String content = "Dear [[name]],<br>"
      + "Please click the link below to verify your registration:<br>"
      + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
      + "Thank you,<br>"
      + "Fractal World.";

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setFrom(fromAddress, senderName);
    helper.setTo(toAddress);
    helper.setSubject(subject);

    content = content.replace("[[name]]", name);
    String verifyURL = siteURL + "/verify_code/" + account.getVerificationCode();

    content = content.replace("[[URL]]", verifyURL);

    helper.setText(content, true);

    mailSender.send(message);

  }

  private void sendResetPasswordEmail(AccountName accountName, String siteURL)
    throws MessagingException, UnsupportedEncodingException {
    Account account = accountName.getAccount();
    String name = accountName.getName();
    String toAddress = account.getEmail();
    String fromAddress = "fractget.world@gmail.com";
    String senderName = "Fractal World";
    String subject = "Reset Password";
    String content = "Dear [[name]],<br>"
      + "Please click the link below to reset your password:<br>"
      + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
      + "Thank you,<br>"
      + "Fractal World.";

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setFrom(fromAddress, senderName);
    helper.setTo(toAddress);
    helper.setSubject(subject);

    content = content.replace("[[name]]", name);
    String verifyURL = siteURL + "/reset_password/" + account.getVerificationCode();

    content = content.replace("[[URL]]", verifyURL);

    helper.setText(content, true);

    mailSender.send(message);

  }

  public boolean verify(String verificationCode) {
    Account account = accountRepo.findByVerificationCode(verificationCode);

    if (account == null || account.isEnabled()) {
      return false;
    } else {
      account.setVerificationCode(null);
      account.setEnabled(true);
      accountRepo.save(account);

      return true;
    }

  }

  public String resetPassword(String verificationCode, String password) throws ResourceNotFoundException {
    Account account = accountRepo.findByVerificationCode(verificationCode);
    if (account != null) {
      account.setVerificationResetCode(null);
      account.setPassword(PasswordService.encodePassword(password));
      accountRepo.save(account);
      return "Password successfully changed";
    }
    throw new ResourceNotFoundException();
  }

  public boolean verifyReset(String verificationCode) {
    Account account = accountRepo.findByVerificationCode(verificationCode);

    if (account == null) {
      return false;
    } else {
      return true;
    }

  }

  public List<Account> getAllAccounts () {
    return accountRepo.findAll();
  }

  public Optional<Account> getAccountById (long id){
    return accountRepo.findById(id);
  }

  public long login (AccountDTO accountDTO) throws ResourceNotFoundException, ResourceIncompatibleException, EmailNotVerifiedException {
    Account account = accountRepo.findByEmail(accountDTO.getEmail()).orElseThrow(ResourceNotFoundException::new);
    if (!account.isEnabled()) {
      throw new EmailNotVerifiedException();
    }
    if (!passwordService.matches(accountDTO.getPassword(), account.getPassword())) {
      throw new ResourceIncompatibleException();
    }
    return account.getProfile().getId();
  }

  public boolean existsByEmail (String email){
    return accountRepo.existsByEmail(email);
  }

  public String changePassword(NewPassword newPassword) throws ResourceNotFoundException, ResourceIncompatibleException{
    Account account = accountRepo.findById(newPassword.getId()).orElseThrow(ResourceNotFoundException::new);
    if (!passwordService.matches(newPassword.getPassword(),account.getPassword())) {
      throw new ResourceIncompatibleException();
    }
    if (newPassword.getNewPassword() != null) {
      account.setPassword(passwordService.encodePassword(newPassword.getNewPassword()));
    }

    accountRepo.save(account);
    return "Password successfully changed";
  }

  public Account updateAccount (long id, Account account) {
    Account accountToUpdate = accountRepo.findById(id).get();
    if(account.getEmail() != null) {
      accountToUpdate.setEmail(account.getEmail());
    }
    if(account.getPassword() != null) {
      accountToUpdate.setPassword(account.getPassword());
    }
    if(account.getProfile() != null) {
      accountToUpdate.setProfile(account.getProfile());
    }

    return accountRepo.save(accountToUpdate);
  }

  public void deleteAccount (long id) {
    accountRepo.deleteById(id);
  }

  public boolean accountExists (long id) {
    return accountRepo.existsById(id);
  }
}
