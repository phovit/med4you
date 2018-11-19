package br.edu.unitri.posjava.tcc.med4you.facebook;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.unitri.posjava.tcc.med4you.dto.UserDTO;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class LoginFacebook {

    @Autowired
    private UserService userService;

    public void obterUsuarioFacebook(String code)
            throws MalformedURLException, IOException {

        String retorno = readURL(new URL(this.getAuthURL(code)));
        //System.out.println("--3-->" + this.getAuthURL(code));

        String accessToken = null;
        @SuppressWarnings("unused")
        Integer expires = null;
        String[] pairs = retorno.split("&");
        for (String pair : pairs) {
            accessToken = (String) new JSONObject(pair).get("access_token");
            //expires =  Integer.valueOf(new JSONObject(pair).getInt("expires_in"));
        }

        JSONObject resp = new JSONObject(readURL(new URL(
                "https://graph.facebook.com/me?access_token=" + accessToken)));


        //System.out.println("--2-->" + "https://graph.facebook.com/me?access_token=" + accessToken);

        UsuarioFacebook usuarioFacebook = new UsuarioFacebook(resp);

        UserDTO dto = userService.findByFacebookId(usuarioFacebook.getId());
        if (dto == null) {
            User user = new User();
            user.setName(usuarioFacebook.getName());
            user.setEmail(usuarioFacebook.getEmail());
            user.setFacebookId(usuarioFacebook.getId());
            user.setUsername("fb_"+usuarioFacebook.getId());
            userService.save(user);

        }


        System.out.println("Usuario encontrado: ");
        System.out.println(usuarioFacebook.getName());
        System.out.println(usuarioFacebook.toString());

    }

    private String readURL(URL url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = url.openStream();
        int r;
        while ((r = is.read()) != -1) {
            baos.write(r);
        }
        return new String(baos.toByteArray());
    }

    public String getLoginRedirectURL() {
        return "https://graph.facebook.com/oauth/authorize?client_id="
                + DadosDaAppNoFacebook.CLIENT_ID
                + "&display=page&redirect_uri=" + DadosDaAppNoFacebook.REDIRECT_URI
                + "&scope=public_profile,email";
    }

    public String getAuthURL(String authCode) {
        return "https://graph.facebook.com/oauth/access_token?client_id="
                + DadosDaAppNoFacebook.CLIENT_ID + "&redirect_uri=" + DadosDaAppNoFacebook.REDIRECT_URI
                + "&client_secret=" + DadosDaAppNoFacebook.CLIENT_SECRET + "&code=" + authCode + "&scope=public_profile,email";
    }

}