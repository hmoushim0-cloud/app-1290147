package sa.factory.app1290147.data

import sa.factory.app1290147.model.GovernmentIntegrationRequest
import sa.factory.app1290147.model.TraineeAccreditationRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface GovernmentIntegrationRequestRepository {
    fun getAvailableCatalog(): Flow<List<TraineeAccreditationRecord>>
    fun getActiveRequest(): Flow<GovernmentIntegrationRequest?>
    suspend fun submitRequest(request: GovernmentIntegrationRequest): Result<String>
}

class InMemoryGovernmentIntegrationRequestRepository : GovernmentIntegrationRequestRepository {
    private val _catalog = MutableStateFlow<List<TraineeAccreditationRecord>>(listOf(
        TraineeAccreditationRecord("1", "حزمة ربط الاعتماد والتحقق الفوري", "مزامنة لحظية لسجلات المتدربين مع البوابة المركزية والتحقق من الهوية الوطنية", 5000L, "verified"),
        TraineeAccreditationRecord("2", "محرك أتمتة تصدير الشهادات المعتمدة", "توليد الشهادات الرقمية وتوثيقها تلقائياً لدى الجهات المنظمة عبر واجهات API", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<GovernmentIntegrationRequest?>(null)

    override fun getAvailableCatalog(): Flow<List<TraineeAccreditationRecord>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<GovernmentIntegrationRequest?> = _active.asStateFlow()

    override suspend fun submitRequest(request: GovernmentIntegrationRequest): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
