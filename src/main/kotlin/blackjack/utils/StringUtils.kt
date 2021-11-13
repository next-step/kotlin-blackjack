package blackjack.utils

object StringUtils {

    fun splitText(text: String, delimiter: String): List<String> {
        return text.split(delimiter)
    }
}
