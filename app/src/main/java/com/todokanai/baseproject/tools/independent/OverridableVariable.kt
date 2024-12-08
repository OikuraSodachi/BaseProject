package com.todokanai.baseproject.tools.independent

/**
 * variable:[Type]의 setter에 추가적인 callback 추가
 *
 * [androidx.lifecycle.LiveData]와 닮은듯?
 *
 * @param initialValue initial value of variable
 * @param additionalSetter callback to add on the setter of variable
 * **/
class OverridableVariable<Type:Any>(
    initialValue:Type,
    val additionalSetter:(newValue:Type)->Unit
){
    private var instance = initialValue
    var value: Type
        get() = instance
        set(inputValue) {
            instance = inputValue
            additionalSetter(inputValue)
        }
}