//package com.crayon.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Properties;
//import java.util.regex.Pattern;
//
///**
// * @Author: znly
// * @Description: java邮箱工具类
// * @Date: 2024/4/25 16:22
// */
//@Slf4j
//@Component
//public class JavaMailUntil {
//
//    /**
//     * 163邮箱账号
//     */
//    @Value(value = "${global.163email}")
//    private String email163Name;
//
//    /**
//     * 126邮箱账号
//     */
//    @Value(value = "${global.126email}")
//    private String emailName;
//
//    /**
//     * 163邮箱授权码
//     */
//    @Value(value = "${global.163emailPwd}")
//    private String email163Pwd;
//
//    /**
//     * 126邮箱授权码
//     */
//    @Value(value = "${global.126emailPwd}")
//    private String emailPwd;
//
//    /**
//     * 创建一个邮箱连接会话
//     *
//     * @return
//     */
//    public Session createSession() {
//        //	创建一个配置文件，并保存
//        Properties props = getProperties();
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(email163Name, email163Pwd);
//            }
//        });
//        //控制台打印调试信息
//        session.setDebug(true);
//        return session;
//    }
//
//    /**
//     * 获取配置文件
//     *
//     * @return
//     */
//    private static @NotNull Properties getProperties() {
//        Properties props = new Properties();
//        //	SMTP服务器连接信息
//        //  126——smtp.126.com
//        //  163——smtp.163.com
//        //  qqsmtp.qq.com"
//
//        //  126——465  163——25 邮箱服务器端口号 不同邮箱会有不同
//        props.put("mail.smtp.host", "smtp.163.com");//	SMTP主机名
//        props.put("mail.smtp.port", "465");//	主机端口号
//        props.put("mail.smtp.auth", "true");//	是否需要用户认证
//        props.put("mail.smtp.ssl.enable", "true");//启用ssl加密
//        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");//ssl协议
//        props.put("mail.smtp.starttls.enable", "true");//	启用TlS加密
//        return props;
//    }
//
//
//    /**
//     * 发送文本邮件 增加对邮箱进行校验
//     *
//     * @param subject 邮件主题
//     * @param text    邮件内容
//     * @param email   收件人邮箱
//     */
//    public void sendEmailText(String subject, String text, String email) {
//        //校验邮箱是否合法
//        if (!isValidEmail(email)) {
//            throw new RuntimeException(UserConstant.EMAIL_ILLEGAL);
//        }
//        //创建一个会话
//        Session session = createSession();
//        //	创建邮件对象
//        MimeMessage message = new MimeMessage(session);
//        try {
//            //增加邮件主题和内容是否为空判断
//            if (StringUtils.isBlank(subject)) {
//                throw new RuntimeException("邮件主题不能为空");
//            }
//            if (StringUtils.isBlank(text)) {
//                throw new RuntimeException("邮件内容不能为空");
//            }
//            //设置邮件主题
//            message.setSubject(subject);
//            message.setText(text);
//            //设置发件人地址
//            message.setFrom(new InternetAddress(email163Name));
//            //设置收件人地址
//            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
//            Transport.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
////        return true;
//    }
//
//    /**
//     * 发送带有附件的邮件
//     *
//     * @param subject       主题
//     * @param email         目标邮箱
//     * @param content       内容
//     * @param fileName      附件名
//     * @param multipartFile 附件文件
//     */
//    public void sendEmailWithFile(String subject, String email, String content, String fileName, MultipartFile multipartFile) {
//        //创建会话
//        Session session = createSession();
//        //	创建邮件
//        MimeMessage message = new MimeMessage(session);
//        try {
//            message.setFrom(email163Name);
//            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
//            message.setRecipients(MimeMessage.RecipientType.CC, new InternetAddress[]{new InternetAddress(email), new InternetAddress(email)});
//            //设置邮件主题
//            message.setSubject(subject);
//            //	邮件主体
//            BodyPart textPart = new MimeBodyPart();
//            textPart.setContent(content, "text/html;charset=utf-8");
//
//            //	邮件附件
//            BodyPart filePart = new MimeBodyPart();
//            filePart.setFileName(fileName);
//
//            //	提交附件文件
//            filePart.setContent(multipartFile.getBytes(), multipartFile.getContentType());
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(textPart);
//            multipart.addBodyPart(filePart);
//
//            //	将邮件装入信封
//            message.setContent(multipart);
//            //发送邮件
//            Transport.send(message);
//        } catch (Exception e) {
//            log.info("发送邮件失败:{}", e.getMessage());
//        }
//    }
//
//    /**
//     * 发送带有附件的邮件
//     *
//     * @param subject  邮件主题
//     * @param email    收件人邮箱
//     * @param content  邮件内容
//     * @param fileName 附件显示名字.类型名
//     * @param path     本地文件路径
//     */
//    public void sendEmailWithFile(String subject, String email, String content, String fileName, String path) {
//        //创建会话
//        Session session = createSession();
//        //	创建邮件
//        MimeMessage message = new MimeMessage(session);
//        try {
//            message.setFrom(email163Name);
//            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
//            message.setRecipients(MimeMessage.RecipientType.CC, new InternetAddress[]{new InternetAddress(email), new InternetAddress(email)});
//            //设置邮件主题
//            message.setSubject(subject);
//            //	邮件主体
//            BodyPart textPart = new MimeBodyPart();
//            textPart.setContent(content, "text/html;charset=utf-8");
//
//            //	邮件附件
//            BodyPart filePart = new MimeBodyPart();
//            filePart.setFileName(fileName);
//
//            //	提交附件文件
//            filePart.setDataHandler(new DataHandler(new ByteArrayDataSource(Files.readAllBytes(Paths.get(path)), "application/octet-stream")));
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(textPart);
//            multipart.addBodyPart(filePart);
//
//            //	将邮件装入信封
//            message.setContent(multipart);
//            //发送邮件
//            Transport.send(message);
//        } catch (Exception e) {
//            log.info("发送邮件失败:{}", e.getMessage());
//        }
//    }
//
//    private boolean isValidEmail(String email) {
//        if (StringUtils.isBlank(email)) {
//            return false;
//        }
//        //先校验邮箱格式是否正确
//        String regex = UserConstant.EMAIL_REGEX;
//        Pattern EMAIL_REGEX = Pattern.compile(regex);
//        if (!EMAIL_REGEX.matcher(email).matches()) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "JavaMailUntil{" +
//                "email163Name='" + email163Name + '\'' +
//                ", emailName='" + emailName + '\'' +
//                ", email163Pwd='" + email163Pwd + '\'' +
//                ", emailPwd='" + emailPwd + '\'' +
//                '}';
//    }
//}
