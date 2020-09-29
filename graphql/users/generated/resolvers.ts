import inputMapper from "../graphql/inputMapper";

export default {
  Query: {
    user: (_: any, { id }: { id: string }, { dataSources }: { dataSources: any }) =>
      dataSources.api.get(`/users/${id}`)
  },

  Mutation: {
    user: () => ({}) // UserMutations
  },

  UserMutations: {
    createUser: (_: any, { body }: { body: any }, { dataSources }: { dataSources: any }) =>
      dataSources.api.post("/users", inputMapper("UserFormInput", body)),

    patchUser: (_: any, { body }: { body: any }, { dataSources }: { dataSources: any }) =>
      dataSources.api.patch("/users", inputMapper("UserPatchFormInput", body)),

    deleteUser: (_: any, { id }: { id: string }, { dataSources }: { dataSources: any }) =>
      dataSources.api.delete(`/users/${id}`, {})
  },

  UserPatchForm: {
    __resolveType(obj: any, _: any, __: any) {
      switch (obj.discriminator) {
        case "user_status_patch_form":
          return "UserStatusPatchForm";
        case "status":
          return "UserStatusPatchForm";
        case "user_email_patch_form":
          return "UserEmailPatchForm";
        case "email":
          return "UserEmailPatchForm";
      }
      throw `Unable to resolve discriminator '${obj.discriminator}' for union 'UserPatchForm'`;
    }
  },

  UserPatchFormDiscriminator: {
    STATUS: "status",
    EMAIL: "email"
  },

  UserStatus: {
    ACTIVE: "active",
    INACTIVE: "inactive"
  }
}