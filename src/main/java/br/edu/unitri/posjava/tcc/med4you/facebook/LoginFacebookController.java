package br.edu.unitri.posjava.tcc.med4you.facebook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@EnableAutoConfiguration
@EnableWebMvc
public class LoginFacebookController {

    @Autowired
    private LoginFacebook loginFacebook;

    /**
     * Método que chama URL do facebook onde o usuário poderá autorizar a aplicação
     * a acessar seus recursos privados.
     *
     * @return
     */
    @RequestMapping("/loginfb")
    public ResponseEntity<String> logarComFacebook() {

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set("Location", loginFacebook.getLoginRedirectURL());

        //System.out.println("---->"+loginFacebook.getLoginRedirectURL());

        return  new ResponseEntity<String>(responseHeaders, HttpStatus.FOUND);
        //return response;//"redirect:/" + loginFacebook.getLoginRedirectURL();
    }

    /**
     * Executado quando o Servidor de Autorização fizer o redirect.
     *
     * @param code
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    @RequestMapping(value = "/loginfbresponse",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String>  logarComFacebook(String code) throws MalformedURLException, IOException {

        loginFacebook.obterUsuarioFacebook(code);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set("Location", "/login");

        SecurityContextHolder.getContext().getAuthentication();

        return  new ResponseEntity<String>(responseHeaders, HttpStatus.FOUND);
    }

}