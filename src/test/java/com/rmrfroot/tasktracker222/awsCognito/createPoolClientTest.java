package com.rmrfroot.tasktracker222.awsCognito;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;


class createPoolClientTest {


    ///////////////////////////////////////////////////////////////

    /*@Mock
    private AWSCognitoIdentityProvider mockAwsCognitoIdentityProvider;
    @Mock
    private AdminInitiateAuthResult mockAdminInitiateAuthResult;
    @Mock
    private AuthenticationResultType mockAuthenticationResultType;

    private createPoolClient create_pool_client;

    final String ACCESS_KEY=System.getenv("ACCESS_KEY");
    final String SECRET_KEY=System.getenv("SECRET_KEY");

    /*private AWSCognitoIdentityProvider createCognitoClient() {
        AWSCredentials cred = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion("us-west-1")
                .build();
    }*/

    /*@BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        create_pool_client = new createPoolClient();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserInfo() {
    }

    @Test
    void deleteUserByUsername() {
    }

    @Test
    void updatePassword() {
    }*/


}