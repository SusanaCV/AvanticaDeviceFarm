/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.WS.Device;

import Controller.ConectionDB;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 *
 * @author jarcec-as
 */
public class adClass {
/*public static void main(String[] args) {
ADConeection("scorrales-as","ScV1503#");
}*/
    public adClass() {
    }
        
    public static LdapContext ADConeection(String user, String password){
        
        LdapContext ldap = null; // Create a new Ldap object        
        try{
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, "AVANTEK-SC\\"+user);//marcos.castro
            env.put(Context.SECURITY_CREDENTIALS, password);//123456abC
            env.put(Context.PROVIDER_URL, "ldap://192.168.188.2:389");
            ldap = new InitialLdapContext(env, null);
            System.out.println("Connection Successful.");
        }catch(NamingException nex){
            System.out.println("LDAP Connection: FAILED");
            nex.printStackTrace();
        }
        return ldap;
    }
    
    public String getUserBasicAttributes(String username,String password, LdapContext ctx) {
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "telephonenumber", "sAMAccountName", "name"};
            constraints.setReturningAttributes(attrIDs);
            username = username.replace(" ", "*");
            NamingEnumeration answer = ctx.search("DC=avantek-sc,DC=avantica,DC=avanticatec,DC=net", "(sAMAccountName=*" + username+"*)", constraints);

            while (answer.hasMoreElements()) {                
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                System.err.println("----------------");
                System.out.println("Nombre: " + attrs.get("name"));
                System.out.println("Email: " + attrs.get("mail"));
                System.out.println("sAMAccountName: " + attrs.get("sAMAccountName"));
             
               if( attrs.get("mail")!=null){
               ConectionDB.addUser(attrs.get("name").remove(0).toString(),attrs.get("mail").remove(0).toString(),0,"");    
               }
               
            return attrs.get("mail").remove(0).toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return username;
    }  
    
   
}
