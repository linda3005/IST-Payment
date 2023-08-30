package templates.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/**").permitAll() // Memperbolehkan akses ke semua permintaan tanpa autentikasi
                                .antMatchers("/auth/**").permitAll() // Memperbolehkan akses ke semua permintaan tanpa autentikasi
                                .antMatchers("/payments/**").permitAll() // Memperbolehkan akses ke semua permintaan tanpa autentikasi

                                .anyRequest().authenticated()
                .and()
            .csrf().disable(); // Menonaktifkan fitur CSRF (opsional, tergantung kebutuhan Anda)
}
}