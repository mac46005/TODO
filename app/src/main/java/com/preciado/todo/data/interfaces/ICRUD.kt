package com.preciado.todo.data.interfaces

interface ICRUD<T>: ICreate<T>, IRead<T>, IUpdate<T>, IDelete<T>