package blackjack

@JvmInline
value class CardNumber(private val number: Int) {
    init {
        require(number in MIN_NUMBER..MAX_NUMBER) {
            "카드 숫자는 $MIN_NUMBER 이상 $MAX_NUMBER 이하여야 합니다. input = $number"
        }
    }

    companion object {
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 13
    }
}