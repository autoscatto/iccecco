workflow "Test Workflow" {
  on = "push"
  resolves = ["GitHub Action for Apache Mmaven"]
}

action "GitHub Action for Apache Mmaven" {
  uses = "./action-mvn"
  runs = "mvn test"
}
