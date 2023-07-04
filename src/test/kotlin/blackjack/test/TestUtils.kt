package blackjack.test

object TestUtils {
    fun randomString(size: Int): String {
        val allCharacters = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return allCharacters.shuffled().subList(0, size).joinToString("")
    }
}
