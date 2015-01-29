/**
 * This file is part of Everit - Authenticated Authorization QDSL Util RI.
 *
 * Everit - Authenticated Authorization QDSL Util RI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Authenticated Authorization QDSL Util RI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Authenticated Authorization QDSL Util RI.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.authnr.qdsl.util.ri.internal;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.authentication.context.AuthenticationContext;
import org.everit.osgi.authnr.qdsl.util.AuthnrQdslUtil;
import org.everit.osgi.authnr.qdsl.util.ri.AuthnrQdslUtilRIConstants;
import org.everit.osgi.authorization.qdsl.util.AuthorizationQdslUtil;
import org.osgi.framework.Constants;

import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.BooleanExpression;

@Component(name = AuthnrQdslUtilRIConstants.SERVICE_FACTORYPID, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE, metatype = true)
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, propertyPrivate = false,
                value = AuthnrQdslUtilRIConstants.DEFAULT_SERVICE_DESCRIPTION),
        @Property(name = AuthnrQdslUtilRIConstants.PROP_AUTHENTICATION_CONTEXT),
        @Property(name = AuthnrQdslUtilRIConstants.PROP_AUTHORIZATION_QDSL_UTIL)
})
@Service
public class AuthnrQdslUtilComponent implements AuthnrQdslUtil {

    @Reference(bind = "setAuthenticationContext")
    private AuthenticationContext authenticationContext;

    @Reference(bind = "setAuthorizationQdslUtil")
    private AuthorizationQdslUtil authorizationQdslUtil;

    @Override
    public BooleanExpression authorizationPredicate(final Expression<Long> targetResourceId, final String... actions) {
        long authorizedResourceId = authenticationContext.getCurrentResourceId();
        return authorizationQdslUtil.authorizationPredicate(authorizedResourceId, targetResourceId, actions);
    }

    public void setAuthenticationContext(final AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public void setAuthorizationQdslUtil(final AuthorizationQdslUtil authorizationQdslUtil) {
        this.authorizationQdslUtil = authorizationQdslUtil;
    }

}
