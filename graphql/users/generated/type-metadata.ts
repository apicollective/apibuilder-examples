
class TypeData {
  constructor(readonly name: string, readonly kind: string) {}
}

class EnumValue {
  constructor(readonly graphQLName: string, readonly serverName: string) {}
}

export class EnumData {
  private readonly byGraphQLName: {
    [_: string]: EnumValue;
  } = {};

  constructor(readonly name: string, readonly values: EnumValue[]) {
    values.forEach((f) => (this.byGraphQLName[f.graphQLName] = f));
  }

  getEnumValue(graphQLName: string): EnumValue | null {
    return this.byGraphQLName[graphQLName];
  }
}

class FieldData {
  constructor(
    readonly graphQLName: string,
    readonly serverName: string,
    readonly type: TypeData
  ) {}
}

export class ModelData {
  private readonly byGraphQLName: {
    [_: string]: FieldData;
  } = {};

  constructor(readonly name: string, readonly fields: FieldData[]) {
    fields.forEach((f) => (this.byGraphQLName[f.graphQLName] = f));
  }

  getField(name: string): FieldData | null {
    return this.byGraphQLName[name];
  }
}

type AnyTypeData = ModelData | EnumData;

class Models {
  private readonly byGraphQLName: {
    [_: string]: AnyTypeData;
  } = {};

  constructor(readonly models: AnyTypeData[]) {
    models.forEach((f) => (this.byGraphQLName[f.name] = f));
  }

  getTypeData(name: string): AnyTypeData | null {
    return this.byGraphQLName[name];
  }

  getModelField(name: string, fieldName: string): FieldData | null {
    const m = this.getTypeData(name);
    if (m) {
      if (m instanceof ModelData) {
        return m.getField(fieldName);
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}

const field = (
  graphQLName: string,
  serverName: string,
  typeName: string,
  typeKind: string
) => new FieldData(graphQLName, serverName, new TypeData(typeName, typeKind));


export const models = new Models([
  new EnumData("UserStatus", [
    new EnumValue("ACTIVE", "active"),
    new EnumValue("INACTIVE", "inactive")
  ]),
  new ModelData("User", [
    field("id", "id", "String", "scalar"),
    field("status", "status", "UserStatus", "enum"),
    field("email", "email", "String", "scalar")
  ]),
  new ModelData("UserForm", [
    field("status", "status", "UserStatus", "enum"),
    field("email", "email", "String", "scalar")
  ]),
  new ModelData("UserStatusPatchForm", [
    field("status", "status", "UserStatus", "enum")
  ]),
  new ModelData("UserEmailPatchForm", [
    field("email", "email", "String", "scalar")
  ]),
  new EnumData("UserPatchFormDiscriminator", [
    new EnumValue("STATUS", "status"),
    new EnumValue("EMAIL", "email")
  ]),
  new ModelData("UserInput", [
    field("id", "id", "String", "scalar"),
    field("status", "status", "UserStatus", "enum"),
    field("email", "email", "String", "scalar")
  ]),
  new ModelData("UserFormInput", [
    field("status", "status", "UserStatus", "enum"),
    field("email", "email", "String", "scalar")
  ]),
  new ModelData("UserStatusPatchFormInput", [
    field("status", "status", "UserStatus", "enum")
  ]),
  new ModelData("UserEmailPatchFormInput", [
    field("email", "email", "String", "scalar")
  ]),
  new ModelData("UserPatchFormInput", [
    field("discriminator", "discriminator", "UserPatchFormDiscriminator", "enum"),
    field("status", "status", "UserStatus", "enum"),
    field("email", "email", "String", "scalar")
  ])
]);