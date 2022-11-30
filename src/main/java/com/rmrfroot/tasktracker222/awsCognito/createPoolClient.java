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

    final String ACCESS_KEY = System.getenv("ACCESS_KEY");
    final String SECRET_KEY = System.getenv("SECRET_KEY");
    final String USER_POOL_ID = System.getenv("USER_POOL_ID");
    final String REGION = System.getenv("AWS_REGION");

    private AWSCognitoIdentityProvider createCognitoClient() {
        AWSCredentials cred = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(REGION)
                .build();
    }

    @Override
    public List<String> getUserInfo(String username) {

        AWSCognitoIdentityProvider cognitoClient = createCognitoClient();
        AdminGetUserRequest userRequest = new AdminGetUserRequest()
                .withUsername(username)
                .withUserPoolId(USER_POOL_ID);

        List<String> list=new ArrayList<>();
        AdminGetUserResult userResult = cognitoClient.adminGetUser(userRequest);
        for(AttributeType attribute: userResult.getUserAttributes()) {
                list.add(attribute.getValue());
        }

        cognitoClient.shutdown();
        return list;
    }



    @Override
    public void deleteUserByUsername(String username) {
        AWSCognitoIdentityProvider cognitoClient = createCognitoClient();

        /*
            Sign user out all devices
         */
        AdminUserGlobalSignOutRequest adminUserGlobalSignOutRequest=new AdminUserGlobalSignOutRequest();
        adminUserGlobalSignOutRequest.withUsername(username).withUserPoolId(USER_POOL_ID);
        cognitoClient.adminUserGlobalSignOut(adminUserGlobalSignOutRequest);

        /*
            Delete user
         */
        AdminDeleteUserRequest request=new AdminDeleteUserRequest();
        request.withUsername(username)
                .withUserPoolId(USER_POOL_ID);
        cognitoClient.adminDeleteUser(request);

        cognitoClient.shutdown();
        System.out.println("Deleted user [" + username + "]");
    }
}
