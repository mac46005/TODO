package com.preciado.todo.core.models.app_models.interfaces

/**
 * Represents a task a user will accomplish.
 * It contains information about the task such as [IDueOn]
 * or [ICompletedOn]
 *
 * @param T The id type for this Task
 * @param U The id type for the [ITaskSet]
 */
interface ITask<T,U>: IModel<ITask<T, U>, T>, IName, IDetails, ICreatedOn, IIsCompleted,
    ICompletedOn, IDueOn, ITaskFrequency {
    var taskSet: IModel<ITaskSet<U>, U>
}