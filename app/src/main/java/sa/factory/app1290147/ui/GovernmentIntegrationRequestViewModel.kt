package sa.factory.app1290147.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290147.data.GovernmentIntegrationRequestRepository
import sa.factory.app1290147.model.GovernmentIntegrationRequest
import sa.factory.app1290147.model.TraineeAccreditationRecord
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GovernmentIntegrationRequestViewModel(
    private val repository: GovernmentIntegrationRequestRepository
) : ViewModel() {
    val catalog: StateFlow<List<TraineeAccreditationRecord>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<GovernmentIntegrationRequest?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: GovernmentIntegrationRequest) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
