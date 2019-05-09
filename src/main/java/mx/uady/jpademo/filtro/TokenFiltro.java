package mx.uady.jpademo.filtro;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import mx.uady.jpademo.model.Usuario;
import mx.uady.jpademo.repository.UsuarioRepository;

/**
 * TokenFiltro
 */
public class TokenFiltro extends GenericFilterBean {
	private final UsuarioRepository usuarioRepo;

	public TokenFiltro(UsuarioRepository usuarioRepository) {
		this.usuarioRepo = usuarioRepository;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Entro al filtro *************************************");

		HttpServletRequest servRequest = (HttpServletRequest) request;
		String token = servRequest.getHeader(HttpHeaders.AUTHORIZATION);

		// Usuario savedToken = usuarioRepo.findByToken(token);
		// boolean done = true;
		/*
		 * try{ Usuario as = savedToken.get(0); }catch(Exception e){ done = false; }
		 */

		// if (savedToken != null) {
		// System.out.println(token);

		Authentication auth = new UsernamePasswordAuthenticationToken(token, null, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// }
		chain.doFilter(request, response);

		// importante

	}

}