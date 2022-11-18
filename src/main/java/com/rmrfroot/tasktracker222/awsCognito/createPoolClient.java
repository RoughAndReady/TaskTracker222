package com.rmrfroot.tasktracker222.awsCognito;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class createPoolClient implements PoolClientInterface {

    final String ACCESS_KEY=System.getenv("ACCESS_KEY");
    final String SECRET_KEY=System.getenv("SECRET_KEY");

    private AWSCognitoIdentityProvider createCognitoClient() {
        AWSCredentials cred = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion("us-west-1")
                .build();
    }

    @Override
    public List<String> getUserInfo(String username) {

        AWSCognitoIdentityProvider cognitoClient = createCognitoClient();
        AdminGetUserRequest userRequest = new AdminGetUserRequest()
                .withUsername(username)
                .withUserPoolId("us-west-1_NHU41muCf");
        /*AdminDeleteUserRequest request=new AdminDeleteUserRequest();
        request.withUsername("admins")
                .withUserPoolId("us-west-1_NHU41muCf");
        cognitoClient.adminDeleteUser(request);
        AdminUpdateUserAttributesRequest admin=new AdminUpdateUserAttributesRequest()
                .withUserAttributes(new AttributeType().withName("name").withValue("visoth cheam"))
                .withUsername("visoth99").withUserPoolId("us-west-1_NHU41muCf");

        cognitoClient.adminUpdateUserAttributes(admin);*/
        List<String> list=new ArrayList<>();
        AdminGetUserResult userResult = cognitoClient.adminGetUser(userRequest);
        for(AttributeType attribute: userResult.getUserAttributes()) {
                //System.out.println(attribute.getValue());
                list.add(attribute.getValue());
        }
        //AttributeType att=userResult.getUserAttributes().get(0);
        //System.out.println(userResult.getUserAttributes());
        //System.out.println(att.getValue());
        cognitoClient.shutdown();
        return list;
    }



    @Override
    public void deleteUserByUsername(String username) {
        AWSCognitoIdentityProvider cognitoClient = createCognitoClient();
        AdminDeleteUserRequest request=new AdminDeleteUserRequest();
        request.withUsername(username)
                .withUserPoolId("us-west-1_NHU41muCf");
        cognitoClient.adminDeleteUser(request);
        AdminUserGlobalSignOutRequest adminUserGlobalSignOutRequest=new AdminUserGlobalSignOutRequest();
        adminUserGlobalSignOutRequest.withUsername(username).withUserPoolId("us-west-1_NHU41muCf");
        cognitoClient.adminUserGlobalSignOut(adminUserGlobalSignOutRequest);
        cognitoClient.shutdown();
        System.out.println("You have deleted the user, the name is "+username);
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String accessToken,String username) {
        AWSCognitoIdentityProvider cognitoClient = createCognitoClient();
        ChangePasswordRequest changePasswordRequest=new ChangePasswordRequest()
                .withAccessToken(accessToken)
                .withPreviousPassword(oldPassword)
                .withProposedPassword(newPassword);
        cognitoClient.changePassword(changePasswordRequest);

        AdminUserGlobalSignOutRequest adminUserGlobalSignOutRequest=new AdminUserGlobalSignOutRequest();
        adminUserGlobalSignOutRequest.withUsername(username).withUserPoolId("us-west-1_NHU41muCf");
        cognitoClient.adminUserGlobalSignOut(adminUserGlobalSignOutRequest);
        cognitoClient.shutdown();
        System.out.println("You have updated the user password, the name is "+username);
    }

}
