package blackjack_dealer.ui

fun String.validateInputValue() {
    require(startsWith(DELIMITER).not()) { INVALID_INPUT }
    require(endsWith(DELIMITER).not()) { INVALID_INPUT }
    require(REGEX.find(this) == null) { INVALID_INPUT }
}

// 임의로 정한 규칙 베팅 금액은 0원부터 가능
fun Int.validateBetAmountValue() {
    require(this > -1) { INVALID_INPUT }
}

private const val DELIMITER = ','
private const val INVALID_INPUT = "올바른 값이 입력되지 않았습니다."
private val REGEX = "[^(\\w+,)]".toRegex()
