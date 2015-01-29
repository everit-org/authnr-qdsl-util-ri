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
import org.everit.osgi.authentication.context.AuthenticationContext;

@Component
@Service
public class MockAuthenticationContextComponent implements AuthenticationContext {

    public static final long CURRENT_RESOURCE_ID = 1;

    @Override
    public long getCurrentResourceId() {
        return CURRENT_RESOURCE_ID;
    }

    @Override
    public long getDefaultResourceId() {
        throw new UnsupportedOperationException();
    }

}
