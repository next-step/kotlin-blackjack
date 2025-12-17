package blackjack.util

object InputUtils {
    fun <T> retryInput(prompt: () -> T): T {
        while (true) {
            try {
                return prompt()
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
