workflow "Test Workflow" {
  on = "push"
  resolves = ["./action-mvn"]
}

action "./action-mvn" {
  uses = "./action-mvn"
  secrets = ["SERVER_REPOID", "SERVER_PASSWORD", "SERVER_REPONAME", "SERVER_USERNAME"]
  runs = "mvn deploy -Dserver.repoid=${SERVER_REPOID} -Dserver.reponame=${SERVER_REPONAME} -Dserver.password=${SERVER_PASSWORD} -Dserver.username=${SERVER_USERNAME}"
}
