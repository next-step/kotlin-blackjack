package blackjack.utils

object StringUtils {

    fun replaceWhiteSpaceAndSplitByComma(target: String): List<String> {
        return target.trim().replace("\\s".toRegex(), "").split(",")
    }
}
