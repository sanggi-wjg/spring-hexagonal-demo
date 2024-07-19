rootProject.name = "spring-boot-kotlin-hexagonal-demo"

include("domain")
include("domain-test")
include("application")
include("infra")

include("adapter:inbound:web")
include("adapter:inbound:event")

include("adapter:outbound:persistence")
include("adapter:outbound:external")
