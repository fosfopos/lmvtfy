package com.chrisrebert.lmvtfy.server

import com.typesafe.config.Config
import akka.actor.ActorSystem
import akka.actor.Extension
import akka.actor.ExtensionId
import akka.actor.ExtensionIdProvider
import akka.actor.ExtendedActorSystem
import akka.util.ByteString
import com.chrisrebert.lmvtfy.util.Utf8String

class SettingsImpl(config: Config) extends Extension {
  val RepoFullName: String = config.getString("lmvtfy.github-repo-to-watch")
  val BotUsername: String = config.getString("lmvtfy.username")
  val WebHookSecretKey: ByteString = ByteString(config.getString("lmvtfy.web-hook-secret-key").utf8Bytes)
}
object Settings extends ExtensionId[SettingsImpl] with ExtensionIdProvider {
  override def lookup() = Settings
  override def createExtension(system: ExtendedActorSystem) = new SettingsImpl(system.settings.config)
  override def get(system: ActorSystem): SettingsImpl = super.get(system)
}
