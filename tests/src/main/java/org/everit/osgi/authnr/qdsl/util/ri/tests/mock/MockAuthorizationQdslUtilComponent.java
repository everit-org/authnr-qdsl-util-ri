/**
 * This file is part of Everit - Authenticated Authorization QDSL Util RI Tests.
 *
 * Everit - Authenticated Authorization QDSL Util RI Tests is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Authenticated Authorization QDSL Util RI Tests is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Authenticated Authorization QDSL Util RI Tests.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.authnr.qdsl.util.ri.tests.mock;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.authnr.qdsl.util.ri.tests.AuthnrQdslUtilTest;
import org.everit.osgi.authorization.qdsl.util.AuthorizationQdslUtil;
import org.junit.Assert;

import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.template.BooleanTemplate;

@Component
@Service
public class MockAuthorizationQdslUtilComponent implements AuthorizationQdslUtil {

    public static final BooleanExpression AUTHORIZATION_PREDICATE = BooleanTemplate.FALSE;

    public static final Expression<Long> TARGET_RESOURCE_ID_EXPRESSION =
            ConstantImpl.create(AuthnrQdslUtilTest.TARGET_RESOURCE_ID);

    @Override
    public BooleanExpression authorizationPredicate(final long authorizedResourceId,
            final Expression<Long> targetResourceId, final String... actions) {
        Assert.assertEquals(MockAuthenticationContextComponent.CURRENT_RESOURCE_ID, authorizedResourceId);
        Assert.assertEquals(TARGET_RESOURCE_ID_EXPRESSION, targetResourceId);
        Assert.assertArrayEquals(AuthnrQdslUtilTest.ACTIONS, actions);
        return AUTHORIZATION_PREDICATE;
    }

}
