package net.bryansaunders.legendary.rest.security;

/*
 * #%L
 * Legendary Card Randomizer
 * %%
 * Copyright (C) 2015 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

/**
 * Rest Security Interceptor.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Provider
public class RestSecurityInterceptor implements ContainerRequestFilter, ExceptionMapper<Exception> {

    /**
     * Auth Property.
     */
    private static final String AUTHORIZATION_PROPERTY = "Authorization";

    /**
     * Auth Type.
     */
    private static final String AUTHENTICATION_SCHEME = "Basic";

    /**
     * Access Denied.
     */
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401,
            new Headers<Object>());

    /**
     * Access Forbidden.
     */
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403,
            new Headers<Object>());

    /**
     * Server Error.
     */
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500,
            new Headers<Object>());

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container
     * .ContainerRequestContext)
     */
    @Override
    public void filter(final ContainerRequestContext requestContext) {
        final ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext
                .getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");

        final Method method = methodInvoker.getMethod();

        // Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {

            // Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(RestSecurityInterceptor.ACCESS_FORBIDDEN);
                return;
            }

            // Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            // Fetch authorization header
            final List<String> authorization = headers.get(RestSecurityInterceptor.AUTHORIZATION_PROPERTY);

            // If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(RestSecurityInterceptor.ACCESS_DENIED);
                return;
            }

            // Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(
                    RestSecurityInterceptor.AUTHENTICATION_SCHEME + " ", "");

            // Decode username and password
            String usernameAndPassword = null;
            try {
                usernameAndPassword = new String(Base64.decode(encodedUserPassword));
            } catch (final IOException e) {
                requestContext.abortWith(RestSecurityInterceptor.SERVER_ERROR);
                return;
            }

            // Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            // Verifying Username and password
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            // Verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                final RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                final Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                // Is user valid?
                if (!this.isUserAllowed(username, password, rolesSet)) {
                    requestContext.abortWith(RestSecurityInterceptor.ACCESS_DENIED);
                    return;
                }
            }
        }
    }

    /**
     * Determines if the User is Valid.
     * 
     * @param username
     *            Username
     * @param password
     *            Password
     * @param rolesSet
     *            Role Set
     * @return true if valid
     */
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
        boolean isAllowed = false;

        // Step 1. Fetch password from database and match with password in
        // argument
        // If both match then get the defined role for user from database and
        // continue; else return isAllowed [false]
        // Access the database and do this part yourself
        // String userRole = userMgr.getUserRole(username);
        final String allowedUser = "admin";
        final String allowedPass = "admin";

        String userRole = "";
        if (username.equals(allowedUser) && password.equals(allowedPass)) {
            userRole = "ADMIN";

            // Verify user role
            if (rolesSet.contains(userRole)) {
                isAllowed = true;
            }
        } else {
            isAllowed = false;
        }

        return isAllowed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(final Exception exception) {
        // TODO Auto-generated method stub
        return null;
    }

}
