echo "Invoking the 'graphql' code generator:"

# If you only want the GraphQL Schema:
apibuilder code apicollective examples-graphql-users latest graphql ./generated


echo ""
echo "Invoking the 'graphql_apollo' code generator:"

# Or if you want the GraphQL Schema and TypeScript adapters for Apollo Server:
apibuilder code apicollective examples-graphql-users latest graphql_apollo ./generated

