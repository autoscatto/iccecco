workflow "Test Workflow" {
  on = "push"
  resolves = ["./action-mvn"]
}

action "./action-mvn" {
  uses = "./action-mvn"
  args = "mvn test"
}
