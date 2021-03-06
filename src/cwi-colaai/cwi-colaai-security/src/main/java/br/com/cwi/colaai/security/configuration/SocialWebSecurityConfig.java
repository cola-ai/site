package br.com.cwi.colaai.security.configuration;

import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author erico.loewe
 */
@Configuration
public class SocialWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PAGINAS_AUTORIZADAS = new String[] { 
        "/usuario/cadastrar**", "/rest/usuario/naoExisteUsuarioComEmail", "/rest/usuario/existeUsuarioComEmail", "/acess/login**", "/acess/esqueceuSenha**",
        "/acess/alterarSenha**", "/token/confirma**", "/css/**", "/js/**", "/media/**", "/bundle/**", "/fonts/**", 
        "/token/recuperarSenha**"
    };
    
    @Autowired
    SocialUserDetailsService socialUserDetailsService;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers(PAGINAS_AUTORIZADAS)
                .permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/acess/login").defaultSuccessUrl("/", true).failureUrl("/acess/login?error").permitAll()
                .and().logout().logoutUrl("/acess/logout").deleteCookies("JSESSIONID").permitAll()
                .and().rememberMe()
                .and().csrf().disable();
    }

    @Autowired
    public void setDetailsService(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(socialUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
