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

    public adClass() {
    }
        
    public LdapContext ADConeection(){
        LdapContext ldap = null; // Create a new Ldap object        
        try{
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, "AVANTICA\\marcos.castro");
            env.put(Context.SECURITY_CREDENTIALS, "123456abC");
            env.put(Context.PROVIDER_URL, "ldap://192.168.188.2:389");
            ldap = new InitialLdapContext(env, null);
            System.out.println("Connection Successful.");
        }catch(NamingException nex){
            System.out.println("LDAP Connection: FAILED");
            nex.printStackTrace();
        }
        return ldap;
    }
    
    public void getUserBasicAttributes(String username, LdapContext ctx) {
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "telephonenumber", "sAMAccountName", "name"};
            constraints.setReturningAttributes(attrIDs);
            username.replace(" ", "*");
            
            NamingEnumeration answer = ctx.search("DC=avantek-sc,DC=avantica,DC=avanticatec,DC=net", "(name=*" + username+"*)", constraints);

            while (answer.hasMoreElements()) {                
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
             
               if( attrs.get("mail")!=null){
               ConectionDB.addUser(attrs.get("name").remove(0).toString(),attrs.get("mail").remove(0).toString(),0,"","12345");    
               }
               
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return;
               
    }  
    
    public String pool(String param){
        return param.replace(" ", "*");
    }
   
}