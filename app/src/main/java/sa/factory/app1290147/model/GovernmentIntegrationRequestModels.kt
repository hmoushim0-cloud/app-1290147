package sa.factory.app1290147.model

import kotlinx.serialization.Serializable

@Serializable
data class TraineeAccreditationRecord(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class GovernmentIntegrationRequest(
    val requestId: String,
    val items: List<TraineeAccreditationRecord>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: GovernmentIntegrationRequestStatus = GovernmentIntegrationRequestStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class GovernmentIntegrationRequestStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
