package ru.nsu.fit.common

import java.util.concurrent.CancellationException

inline fun <R> runCatchingNonCancellation(
    block: () -> R
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}
