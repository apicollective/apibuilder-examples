import inputMapper from "../graphql/inputMapper";

export default {
  Mutation: {
    createUser: (_: any, { body }: { body: any }, { dataSources }: { dataSources: any }) =>
      dataSources.api.post("/users", inputMapper("UserFormInput", body)),

    patchUser: (_: any, { body }: { body: any }, { dataSources }: { dataSources: any }) =>
      dataSources.api.patch("/users", inputMapper("UserPatchFormInput", body)),

    deleteUser: (_: any, { id }: { id: string }, { dataSources }: { dataSources: any }) =>
      dataSources.api.delete(`/users/${id}`, {})
  },

  UserPatchFormDiscriminator: {
    USER_STATUS_PATCH_FORM: "user_status_patch_form",
    USER_EMAIL_PATCH_FORM: "user_email_patch_form"
  },

  Query: {
    user: (_: any, { id }: { id: string }, { dataSources }: { dataSources: any }) =>
      dataSources.api.get(`/users/${id}`)
  },

  UserStatus: {
    ACTIVE: "active",
    INACTIVE: "inactive"
  },

  UserPatchForm: {
    __resolveType(obj: any, _: any, __: any) {
      switch (obj.discriminator) {
        case "user_status_patch_form":
          return "UserStatusPatchForm";
        case "user_email_patch_form":
          return "UserEmailPatchForm";
      }
      throw `Unable to resolve discriminator '${obj.discriminator}' for union 'UserPatchForm'`;
    }
  }
}