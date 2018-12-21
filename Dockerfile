FROM maven:3.6.0-jdk-8-alpine

LABEL version="0.1-SNAPSHOT"
LABEL repository="https://github.com/autoscatto/iccecco"
LABEL homepage="https://github.com/autoscatto/iccecco"
LABEL maintainer="Autoscatto <4utoscatto@gmail.com>"

LABEL com.github.actions.name="GitHub Action for Apache Mmaven"
LABEL com.github.actions.description="Wraps the maven client to enable common mvn commands."
LABEL com.github.actions.icon="package"
LABEL com.github.actions.color="red"

COPY "entrypoint.sh" "/entrypoint.sh"
ENTRYPOINT ["/entrypoint.sh"]
CMD ["help"]