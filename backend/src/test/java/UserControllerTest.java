import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.alibaba.fastjson.JSONObject;
import com.app.backend.common.util.RSAUtil;
import com.app.backend.user.model.User;
import com.app.backend.user.model.UserInfo;
import com.app.backend.user.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1803606771869179905L);
        Mockito.when(userService.getUserInfo(1803606771869179905L)).thenReturn(userInfo);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value(1803606771869179905L));
    }

    @Test
    public void testGetRsaPublicKey() throws Exception {
        String publicKey = "mockPublicKey";
        Mockito.when(RSAUtil.getPublicKeyStr()).thenReturn(publicKey);

        mockMvc.perform(get("/rsa-pks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(publicKey));
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setEmail("example@example.com");
        user.setPassword("password123");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("Success!"));
    }

    @Test
    public void testVerifyToken() throws Exception {
        mockMvc.perform(get("/user-tokens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("Success!"));
    }

    @Test
    public void testLogin() throws Exception {
        User user = new User();
        user.setEmail("example@example.com");
        user.setPassword("password123");
        String token = "mockToken";
        Mockito.when(userService.login(Mockito.any(User.class))).thenReturn(token);

        mockMvc.perform(post("/user-tokens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(token));
    }

    @Test
    public void testUpdateUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1803606771869179905L);

        mockMvc.perform(put("/user-infos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(userInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("Success!"));
    }

    @Test
    public void testUpdateUsers() throws Exception {
        User user = new User();
        user.setId(1803606771869179905L);

        mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.msg").value("Success!"));
    }
}