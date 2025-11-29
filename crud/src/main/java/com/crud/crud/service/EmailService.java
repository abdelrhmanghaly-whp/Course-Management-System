package com.crud.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from}")
    private String fromEmail;

    @Value("${app.email.from}")
    private String adminEmail;

    @Async
    public void sendWelcomeEmail(String toEmail, String username){
        try{
            SimpleMailMessage mess = new SimpleMailMessage();
            mess.setFrom(fromEmail);
            mess.setTo(toEmail);
            mess.setSubject("Welcome to Course Management System");
            mess.setText(
                "Hello "+username+",\n\n"+"Welcome to our Course Management System!\n"+"You can now browse and manage courses.\n\n"
            );

            mailSender.send(mess);
            System.out.println("Welcome email sent to " + toEmail);
        }catch(Exception e){
            System.err.println("Failed to send email: "+e.getMessage());
        }
    }

    @Async
    public void sendCourseCreatedNotification(String courseName, String topicName){
        try{
            SimpleMailMessage mess = new SimpleMailMessage();
            mess.setFrom(fromEmail);
            mess.setTo(adminEmail);
            mess.setSubject("New Course Added: " + courseName);
            mess.setText(
                "A new course has been created!\n\n" +
                "Course Name: " + courseName + "\n" +
                "Topic: " + topicName + "\n\n" +
                "Login to the system to review it."
            );
            mailSender.send(mess);
            System.out.println("Course Creation notification sent to admin");
        }catch(Exception e){
            System.err.println("Failed to send email: "+e.getMessage());
        }
    }

    @Async
    public void sendCourseUpdateNotification(String courseName, String updatedBy){
        try{
            SimpleMailMessage mess = new SimpleMailMessage();
            mess.setFrom(fromEmail);
            mess.setTo(adminEmail);
            mess.setSubject("Course Updated: " + courseName);
            mess.setText(
                "A course has been updated!\n\n" +
                "Course Name: " + courseName + "\n" +
                "Updated by: " + updatedBy + "\n\n" +
                "Check the latest changes."
            );
            mailSender.send(mess);
            System.out.println("Course Update notification sent to admin");
        }catch(Exception e){
            System.err.println("Failed to send email: "+e.getMessage());
        }
    }
    @Async
    public void sendCourseDeletedNotification(String courseName) { 
        try {
            SimpleMailMessage mess = new SimpleMailMessage();
            mess.setFrom(fromEmail);
            mess.setTo(adminEmail);
            mess.setSubject("Course Deleted: " + courseName);
            mess.setText(
                "A course has been deleted!\n\n" +
                "Course Name: " + courseName + "\n\n" +
                "This action cannot be undone."
            );
            mailSender.send(mess);
            System.out.println("Course deletion notification sent");
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    @Async
    public void sendEmail(String to, String subject, String body) { // custom
        try {
            SimpleMailMessage mess = new SimpleMailMessage();
            mess.setFrom(fromEmail);
            mess.setTo(to);
            mess.setSubject(subject);
            mess.setText(body);
            mailSender.send(mess);
            System.out.println("Email sent to: " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
