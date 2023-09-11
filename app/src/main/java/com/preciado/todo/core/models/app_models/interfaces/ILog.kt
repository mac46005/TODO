package com.preciado.todo.core.models.app_models.interfaces

/**
 * Logs the actions done for the [ITask].
 * References both the [ITaskSet] and the [ITask].
 *
 * @param T The id type for this Log
 * @param U The id type for [ITask]
 * @param V The id type for [ITaskSet]
 */
interface ILog<T, U, V>: IModel<ILog<T, U, V>, T>, IDetails, ICreatedOn {
    var taskId: IModel<ITask<U, V>, U>
    var taskSetId: IModel<ITaskSet<V>, V>
}