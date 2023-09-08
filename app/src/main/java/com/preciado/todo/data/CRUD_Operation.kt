package com.preciado.todo.data

enum class CRUD_Operation {
    CREATE,
    READ,
    UPDATE,
    DELETE;

    companion object{
        fun fromInt(int: Int): CRUD_Operation{
            return CRUD_Operation.values().find { it.ordinal == int } ?: throw IllegalArgumentException()
        }
    }
}