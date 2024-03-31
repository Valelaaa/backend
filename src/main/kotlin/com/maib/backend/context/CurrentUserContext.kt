package com.maib.backend.context

object CurrentUserContext {
    private val currentUserThreadLocal: ThreadLocal<String> = ThreadLocal()

    fun getCurrentUserId(): String? {
        return currentUserThreadLocal.get()
    }

    fun setCurrentUserId(userId: String) {
        currentUserThreadLocal.set(userId)
    }

    fun clear() {
        currentUserThreadLocal.remove()
    }
}