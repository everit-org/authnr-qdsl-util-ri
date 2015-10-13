/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.authnr.qdsl.util.ri;

import java.util.Objects;

import org.everit.authentication.context.AuthenticationContext;
import org.everit.authnr.qdsl.util.AuthnrQdslUtil;
import org.everit.authorization.qdsl.util.AuthorizationQdslUtil;

import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * Implementation of {@link AuthnrQdslUtil} interface.
 */
public class AuthnrQdslUtilImpl implements AuthnrQdslUtil {

  private AuthenticationContext authenticationContext;

  private AuthorizationQdslUtil authorizationQdslUtil;

  /**
   * Constructor.
   *
   * @param authenticationContext
   *          {@link AuthenticationContext} instance.
   * @param authorizationQdslUtil
   *          {@link AuthorizationQdslUtil} instance.
   * @throws NullPointerException
   *           if one of the parameter is <code>null</code>.
   */
  public AuthnrQdslUtilImpl(final AuthenticationContext authenticationContext,
      final AuthorizationQdslUtil authorizationQdslUtil) {
    this.authenticationContext =
        Objects.requireNonNull(authenticationContext, "authenticationContext cannot be null");
    this.authorizationQdslUtil =
        Objects.requireNonNull(authorizationQdslUtil, "authorizationQdslUtil cannot be null");
  }

  @Override
  public BooleanExpression authorizationPredicate(final Expression<Long> targetResourceId,
      final String... actions) {
    long authorizedResourceId = authenticationContext.getCurrentResourceId();
    return authorizationQdslUtil.authorizationPredicate(authorizedResourceId, targetResourceId,
        actions);
  }

}
