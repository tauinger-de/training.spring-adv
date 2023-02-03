@EnableWebSecurity -- deaktiviert Standard und ersetzt durch eigene

WebSecurityConfigurerAdapter -- util Klasse zum Bauen eines WebSecurityConfigurer


io.jsonwebtoken.Jwts.parserBuilder().setSigningKey(sessionAuthPublicKey).build();


GenericFilterBean
--> AbstractPreAuthenticatedProcessingFilter
    --> custom.PreAuthenticationFilter


AuthenticationManager
-> ProviderManager


AuthenticationProvider
-> PreAuthenticatedAuthenticationProvider



AuthenticationUserDetailsService
-> own impl


AuthenticationEntryPoint ???


HttpSecurity Methoden
- antMatcher() : HttpSecurity
- addFilterAfter()
- authorizeRequests() : ExpressionUrlAuthorizationConfigurer

SessionManagementConfigurer
- and() -- switch back up

AbstractRequestMatcherRegistry
- antMatchers() -- plural, andere als oben!!
- anyRequest()

ExpressionUrlAuthorizationConfigurer
- permitAll
- anonymous
- authenticated
- fullyAuthenticated
- access
- hasRole vs hasAuthority
- mvcMatchers (vs antMatchers) -- auch in andere Klasse?!