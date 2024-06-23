rootProject.name = "spring-boot-kotlin-hexagonal-demo"

include("domain")
include("application")

//findProject(":adapter:inbound")?.name = "inbound"
//findProject(":adapter:inbound:web")?.name = "web"
//findProject(":adapter:inbound:event")?.name = "event"
//
//findProject(":adapter:outbound")?.name = "outbound"
//findProject(":adapter:outbound:persistence")?.name = "persistence"
//findProject(":adapter:outbound:external")?.name = "external"
//
//include("adapter")
//include("adapter:inbound")
include("adapter:inbound:web")
include("adapter:inbound:event")

//include("adapter:outbound")
include("adapter:outbound:persistence")
include("adapter:outbound:external")
