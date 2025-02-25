package com.todokanai.baseproject.data.dataclass

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/** MutableStateFlow designed for remembering last known value
 * @author Oikura Sodachi
 * @param initialValue initial value of this stateflow (usually from database)
 * @param saveValue save value to database **/
data class SavableStateFlow<Type:Any>(
    val initialValue:Type,
    val saveValue:(value:Type)->Unit
): StateFlow<Type> {
    private val stateFlowInstance = MutableStateFlow<Type>(initialValue)

    fun setValue(value:Type){
        stateFlowInstance.value = value
        saveValue(value)
    }

    override val replayCache: List<Type>
        get() = stateFlowInstance.replayCache
    override val value: Type
        get() = stateFlowInstance.value
    override suspend fun collect(collector: FlowCollector<Type>): Nothing {
        stateFlowInstance.collect(collector)
    }
}