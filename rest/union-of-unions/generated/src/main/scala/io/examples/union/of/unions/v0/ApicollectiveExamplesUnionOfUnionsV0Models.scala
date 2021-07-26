/**
 * Generated by API Builder - https://www.apibuilder.io
 * Service version: 0.0.1
 * apibuilder 0.15.11 app.apibuilder.io/apicollective/examples-union-of-unions/latest/play_2_x_standalone_json
 */
package io.examples.union.of.unions.v0.models {

  sealed trait AbstractGuest extends Party

  /**
   * Defines the valid discriminator values for the type AbstractGuest
   */
  sealed trait AbstractGuestDiscriminator extends _root_.scala.Product with _root_.scala.Serializable

  object AbstractGuestDiscriminator {

    case object Guest extends AbstractGuestDiscriminator { override def toString = "guest" }

    final case class UNDEFINED(override val toString: String) extends AbstractGuestDiscriminator

    val all: scala.List[AbstractGuestDiscriminator] = scala.List(Guest)

    private[this] val byName: Map[String, AbstractGuestDiscriminator] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): AbstractGuestDiscriminator = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[AbstractGuestDiscriminator] = byName.get(value.toLowerCase)

  }

  sealed trait AbstractUser extends Party

  /**
   * Defines the valid discriminator values for the type AbstractUser
   */
  sealed trait AbstractUserDiscriminator extends _root_.scala.Product with _root_.scala.Serializable

  object AbstractUserDiscriminator {

    case object User extends AbstractUserDiscriminator { override def toString = "user" }

    final case class UNDEFINED(override val toString: String) extends AbstractUserDiscriminator

    val all: scala.List[AbstractUserDiscriminator] = scala.List(User)

    private[this] val byName: Map[String, AbstractUserDiscriminator] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): AbstractUserDiscriminator = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[AbstractUserDiscriminator] = byName.get(value.toLowerCase)

  }

  sealed trait Party extends _root_.scala.Product with _root_.scala.Serializable

  /**
   * Defines the valid discriminator values for the type Party
   */
  sealed trait PartyDiscriminator extends _root_.scala.Product with _root_.scala.Serializable

  object PartyDiscriminator {

    case object AbstractGuest extends PartyDiscriminator { override def toString = "abstract_guest" }
    case object AbstractUser extends PartyDiscriminator { override def toString = "abstract_user" }

    final case class UNDEFINED(override val toString: String) extends PartyDiscriminator

    val all: scala.List[PartyDiscriminator] = scala.List(AbstractGuest, AbstractUser)

    private[this] val byName: Map[String, PartyDiscriminator] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): PartyDiscriminator = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[PartyDiscriminator] = byName.get(value.toLowerCase)

  }

  final case class Guest(
    email: _root_.scala.Option[String] = None,
    name: _root_.scala.Option[String] = None
  ) extends AbstractGuest

  final case class User(
    id: String,
    email: String,
    name: _root_.scala.Option[String] = None
  ) extends AbstractUser

  /**
   * Provides future compatibility in clients - in the future, when a type is added
   * to the union AbstractGuest, it will need to be handled in the client code. This
   * implementation will deserialize these future types as an instance of this class.
   *
   * @param description Information about the type that we received that is undefined in this version of
   *        the client.
   */
  final case class AbstractGuestUndefinedType(
    description: String
  ) extends AbstractGuest

  /**
   * Provides future compatibility in clients - in the future, when a type is added
   * to the union AbstractUser, it will need to be handled in the client code. This
   * implementation will deserialize these future types as an instance of this class.
   *
   * @param description Information about the type that we received that is undefined in this version of
   *        the client.
   */
  final case class AbstractUserUndefinedType(
    description: String
  ) extends AbstractUser

  /**
   * Provides future compatibility in clients - in the future, when a type is added
   * to the union Party, it will need to be handled in the client code. This
   * implementation will deserialize these future types as an instance of this class.
   *
   * @param description Information about the type that we received that is undefined in this version of
   *        the client.
   */
  final case class PartyUndefinedType(
    description: String
  ) extends Party

}

package io.examples.union.of.unions.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.examples.union.of.unions.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map { str =>
      _root_.java.util.UUID.fromString(str)
    }

    private[v0] implicit val jsonWritesUUID = new Writes[_root_.java.util.UUID] {
      def writes(x: _root_.java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      _root_.org.joda.time.format.ISODateTimeFormat.dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[_root_.org.joda.time.DateTime] {
      def writes(x: _root_.org.joda.time.DateTime) = {
        JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(x))
      }
    }

    private[v0] implicit val jsonReadsJodaLocalDate = __.read[String].map { str =>
      _root_.org.joda.time.format.ISODateTimeFormat.dateTimeParser.parseLocalDate(str)
    }

    private[v0] implicit val jsonWritesJodaLocalDate = new Writes[_root_.org.joda.time.LocalDate] {
      def writes(x: _root_.org.joda.time.LocalDate) = {
        JsString(_root_.org.joda.time.format.ISODateTimeFormat.date.print(x))
      }
    }

    implicit def jsonReadsExamplesUnionOfUnionsGuest: play.api.libs.json.Reads[Guest] = {
      for {
        email <- (__ \ "email").readNullable[String]
        name <- (__ \ "name").readNullable[String]
      } yield Guest(email, name)
    }

    def jsObjectGuest(obj: io.examples.union.of.unions.v0.models.Guest): play.api.libs.json.JsObject = {
      (obj.email match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("email" -> play.api.libs.json.JsString(x))
      }) ++
      (obj.name match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("name" -> play.api.libs.json.JsString(x))
      }) ++ play.api.libs.json.Json.obj("discriminator" -> "guest")
    }

    implicit def jsonWritesExamplesUnionOfUnionsGuest: play.api.libs.json.Writes[Guest] = {
      new play.api.libs.json.Writes[io.examples.union.of.unions.v0.models.Guest] {
        def writes(obj: io.examples.union.of.unions.v0.models.Guest) = {
          jsObjectGuest(obj)
        }
      }
    }

    implicit def jsonReadsExamplesUnionOfUnionsUser: play.api.libs.json.Reads[User] = {
      for {
        id <- (__ \ "id").read[String]
        email <- (__ \ "email").read[String]
        name <- (__ \ "name").readNullable[String]
      } yield User(id, email, name)
    }

    def jsObjectUser(obj: io.examples.union.of.unions.v0.models.User): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "email" -> play.api.libs.json.JsString(obj.email)
      ) ++ (obj.name match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("name" -> play.api.libs.json.JsString(x))
      }) ++ play.api.libs.json.Json.obj("discriminator" -> "user")
    }

    implicit def jsonWritesExamplesUnionOfUnionsUser: play.api.libs.json.Writes[User] = {
      new play.api.libs.json.Writes[io.examples.union.of.unions.v0.models.User] {
        def writes(obj: io.examples.union.of.unions.v0.models.User) = {
          jsObjectUser(obj)
        }
      }
    }

    implicit def jsonReadsExamplesUnionOfUnionsAbstractGuest: play.api.libs.json.Reads[AbstractGuest] = new play.api.libs.json.Reads[AbstractGuest] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[AbstractGuest] = {
        (js \ "discriminator").asOpt[String].getOrElse { sys.error("Union[AbstractGuest] requires a discriminator named 'discriminator' - this field was not found in the Json Value") } match {
          case "guest" => js.validate[io.examples.union.of.unions.v0.models.Guest]
          case other => play.api.libs.json.JsSuccess(io.examples.union.of.unions.v0.models.AbstractGuestUndefinedType(other))
        }
      }
    }

    def jsObjectAbstractGuest(obj: io.examples.union.of.unions.v0.models.AbstractGuest): play.api.libs.json.JsObject = {
      obj match {
        case x: io.examples.union.of.unions.v0.models.Guest => jsObjectGuest(x)
        case other => {
          sys.error(s"The type[${other.getClass.getName}] has no JSON writer")
        }
      }
    }

    implicit def jsonWritesExamplesUnionOfUnionsAbstractGuest: play.api.libs.json.Writes[AbstractGuest] = {
      new play.api.libs.json.Writes[io.examples.union.of.unions.v0.models.AbstractGuest] {
        def writes(obj: io.examples.union.of.unions.v0.models.AbstractGuest) = {
          jsObjectAbstractGuest(obj)
        }
      }
    }

    implicit def jsonReadsExamplesUnionOfUnionsAbstractUser: play.api.libs.json.Reads[AbstractUser] = new play.api.libs.json.Reads[AbstractUser] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[AbstractUser] = {
        (js \ "discriminator").asOpt[String].getOrElse { sys.error("Union[AbstractUser] requires a discriminator named 'discriminator' - this field was not found in the Json Value") } match {
          case "user" => js.validate[io.examples.union.of.unions.v0.models.User]
          case other => play.api.libs.json.JsSuccess(io.examples.union.of.unions.v0.models.AbstractUserUndefinedType(other))
        }
      }
    }

    def jsObjectAbstractUser(obj: io.examples.union.of.unions.v0.models.AbstractUser): play.api.libs.json.JsObject = {
      obj match {
        case x: io.examples.union.of.unions.v0.models.User => jsObjectUser(x)
        case other => {
          sys.error(s"The type[${other.getClass.getName}] has no JSON writer")
        }
      }
    }

    implicit def jsonWritesExamplesUnionOfUnionsAbstractUser: play.api.libs.json.Writes[AbstractUser] = {
      new play.api.libs.json.Writes[io.examples.union.of.unions.v0.models.AbstractUser] {
        def writes(obj: io.examples.union.of.unions.v0.models.AbstractUser) = {
          jsObjectAbstractUser(obj)
        }
      }
    }

    implicit def jsonReadsExamplesUnionOfUnionsParty: play.api.libs.json.Reads[Party] = new play.api.libs.json.Reads[Party] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[Party] = {
        (js \ "discriminator").asOpt[String].getOrElse { sys.error("Union[Party] requires a discriminator named 'discriminator' - this field was not found in the Json Value") } match {
          case "abstract_guest" => js.validate[io.examples.union.of.unions.v0.models.AbstractGuest]
          case "abstract_user" => js.validate[io.examples.union.of.unions.v0.models.AbstractUser]
          case other => play.api.libs.json.JsSuccess(io.examples.union.of.unions.v0.models.PartyUndefinedType(other))
        }
      }
    }

    def jsObjectParty(obj: io.examples.union.of.unions.v0.models.Party): play.api.libs.json.JsObject = {
      obj match {
        case x: io.examples.union.of.unions.v0.models.AbstractGuest => jsObjectAbstractGuest(x)
        case x: io.examples.union.of.unions.v0.models.AbstractUser => jsObjectAbstractUser(x)
        case other => {
          sys.error(s"The type[${other.getClass.getName}] has no JSON writer")
        }
      }
    }

    implicit def jsonWritesExamplesUnionOfUnionsParty: play.api.libs.json.Writes[Party] = {
      new play.api.libs.json.Writes[io.examples.union.of.unions.v0.models.Party] {
        def writes(obj: io.examples.union.of.unions.v0.models.Party) = {
          jsObjectParty(obj)
        }
      }
    }
  }
}

