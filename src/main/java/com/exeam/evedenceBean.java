package com.exeam;

import static com.sun.faces.facelets.util.Path.context;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="reg", eager = true)
@SessionScoped

public class evedenceBean {
    private int id;
    private String name;
    private String password;
    private String contact;
    private String course;
    private String gender;
    private String[] skill;
    private String address;
    private String message;
    private boolean update;
    private List<evedenceBean> registation;
    private static Connection conn = null;
    private PreparedStatement pst = null;
    ResultSet rs;
    
    public void init(){
        try {
            registation = new ArrayList<>();
            conn = DBAccess.getConn();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(evedenceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(evedenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String stoge(){
        init();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
        String sql = "insert into registation(id, name, password, contact, course, gender, skill, address) values(?, ?, ?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setString(3, password);
        pst.setString(4, contact);
        pst.setString(5, course);
        pst.setString(6, gender);
        pst.setString(7, Arrays.toString(skill));
        pst.setString(8, address);
        int rs = pst.executeUpdate();
            if (rs>0) {
                context.addMessage(null, new FacesMessage("save success"));
            }else{
                context.addMessage(null, new FacesMessage("save failed"));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
     public List<evedenceBean> getShowData() { 
         
        init();
        try {
            String sql = "select * from registation";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                evedenceBean r = new evedenceBean();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String contact = rs.getString("contact");
                String course = rs.getString("course");
                String gender = rs.getString("gender");
                String skill = rs.getString("skill");
                String address = rs.getString("address");                
                
                r.setId(id);
                r.setName(name);
                r.setPassword(password);
                r.setContact(contact);
                r.setCourse(course);
                r.setGender(gender);
                r.setSkill(skill.split(","));
                r.setAddress(address);                
                registation.add(r);       
                
            }

        } catch (SQLException ex) {

        }
        return registation;
    }

     public String edit(){
         try {
             conn = DBAccess.getConn();
             
         } catch (Exception e) {
         }
        return "/index.xhtml";
     }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCourse() {
        return course;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }
    
    

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    public List<evedenceBean> getRegistation() {
        return registation;
    }

    public void setRegistation(List<evedenceBean> registation) {
        this.registation = registation;
    }
    
        
    
}
