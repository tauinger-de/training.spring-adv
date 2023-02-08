io.jsonwebtoken.Jwts.parserBuilder().setSigningKey(sessionAuthPublicKey).build();


GenericFilterBean
--> AbstractPreAuthenticatedProcessingFilter
    --> custom.PreAuthenticationFilter



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