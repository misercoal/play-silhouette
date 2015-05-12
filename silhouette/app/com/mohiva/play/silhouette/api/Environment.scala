/**
 * Copyright 2015 Mohiva Organisation (license at mohiva dot com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mohiva.play.silhouette.api

import com.mohiva.play.silhouette.api.services.{ AuthenticatorService, IdentityService }
import play.api.i18n.MessagesApi

/**
 * The environment needed to instantiate a Silhouette controller.
 */
trait Environment[I <: Identity, T <: Authenticator] {

  /**
   * Gets the identity service implementation.
   *
   * @return The identity service implementation.
   */
  def identityService: IdentityService[I]

  /**
   * Gets the authenticator service implementation.
   *
   * @return The authenticator service implementation.
   */
  def authenticatorService: AuthenticatorService[T]

  /**
   * Gets the list of request providers.
   *
   * @return The list of request providers.
   */
  def requestProviders: Seq[RequestProvider]

  /**
   * The event bus implementation.
   *
   * @return The event bus implementation.
   */
  def eventBus: EventBus

  /**
   * The Play messages API.
   *
   * @return The Play messages API.
   */
  def messagesApi: MessagesApi
}

/**
 * The companion object.
 */
object Environment {
  def apply[I <: Identity, T <: Authenticator](
    identityServiceImpl: IdentityService[I],
    authenticatorServiceImpl: AuthenticatorService[T],
    requestProvidersImpl: Seq[RequestProvider],
    eventBusImpl: EventBus,
    messagesApiImpl: MessagesApi) = new Environment[I, T] {
    val identityService = identityServiceImpl
    val authenticatorService = authenticatorServiceImpl
    val requestProviders = requestProvidersImpl
    val eventBus = eventBusImpl
    val messagesApi = messagesApiImpl
  }
}
