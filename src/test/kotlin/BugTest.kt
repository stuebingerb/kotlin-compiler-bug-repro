import kotlin.test.Test
import kotlin.test.assertEquals

typealias Result<T> = Pair<T?, Exception?>

inline fun <T> Result<T>.onSuccess(block: T.() -> Unit) =
    also { if (first != null) block(first!!) }

inline fun <T> Result<T>.onError(block: Exception.() -> Unit) =
    also { if (second != null) block(second!!) }

fun <T> Result<T>.onSuccessGet() = if (first != null) first!! else throw second!!

fun <T> Result<T>.onErrorFail() =
    also { if (second != null) throw second!! }

class BugTest {

    @Test
    fun `fails with Kotlin 1_5_30 (A)`() {
        try {
            doSomething<String>(null).onErrorFail()
        } catch (expected: NullPointerException) {
        }
    }

    @Test
    fun `fails with Kotlin 1_5_30 (B)`() {
        assertEquals("foo", doSomething<String>("foo").onSuccessGet())
    }

    @Test
    fun `works with both versions (A)`() {
        try {
            doSomething<String>(null).onError { throw this }
        } catch (expected: NullPointerException) {
        }
    }

    @Test
    fun `works with both versions (B)`() {
        doSomething<String>("foo").onSuccess { assertEquals("foo", this) }
    }

    private fun <T> doSomething(input: String?): Result<T> = try {
        // Cause NPE for null inputs
        input!! as T to null
    } catch (e: Exception) {
        null to e
    }
}
