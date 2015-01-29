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
package org.everit.osgi.authnr.qdsl.util.ri.tests;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.authnr.qdsl.util.AuthnrQdslUtil;
import org.everit.osgi.authnr.qdsl.util.ri.tests.mock.MockAuthorizationQdslUtilComponent;
import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.junit.Assert;
import org.junit.Test;

@Component(immediate = true)
@Properties({
        @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TESTRUNNER_ENGINE_TYPE, value = "junit4"),
        @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TEST_ID, value = "AuthnrQdslUtilTest"),
        @Property(name = "authnrQdslUtil.target"),
})
@Service(value = AuthnrQdslUtilTest.class)
public class AuthnrQdslUtilTest {

    public static final long TARGET_RESOURCE_ID = 2;

    public static final String[] ACTIONS = { "a1", "a2" };

    @Reference(bind = "setAuthnrQdslUtil")
    private AuthnrQdslUtil authnrQdslUtil;

    public void setAuthnrQdslUtil(final AuthnrQdslUtil authnrQdslUtil) {
        this.authnrQdslUtil = authnrQdslUtil;
    }

    @Test
    public void testComplex() {
        Assert.assertEquals(
                MockAuthorizationQdslUtilComponent.AUTHORIZATION_PREDICATE,
                authnrQdslUtil.authorizationPredicate(
                        MockAuthorizationQdslUtilComponent.TARGET_RESOURCE_ID_EXPRESSION, ACTIONS));
    }

}
