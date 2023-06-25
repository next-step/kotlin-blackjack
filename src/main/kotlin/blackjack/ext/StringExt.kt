package blackjack.ext

fun replaceWhiteSpaceAndSplitByComma(target: String): List<String> {
    return target.trim().replace("\\s".toRegex(), "").split(",")
}
