package com.preciado.todo.data

enum class CRUDEnum {
    CREATE,
    READ,
    UPDATE,
    DELETE;

    companion object{
        fun fromInt(int: Int): CRUDEnum{
            return CRUDEnum.values().find { it.ordinal == int } ?: throw IllegalArgumentException()
        }
    }
}