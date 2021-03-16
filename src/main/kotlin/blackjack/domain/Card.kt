package blackjack.domain

class Card private constructor(val value: Pair<String, Pattern>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value.first + value.second.pattern
    }

    companion object {
        val NUMBERS =
            listOf<String>("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "J", "Q", "K")
        val PATTERNS = listOf(
            Pattern.HEART,
            Pattern.DIAMOND,
            Pattern.SPADE,
            Pattern.CLOVER
        )

        fun makeCard(number: String, pattern: Pattern): Card {
            checkValidateNumber(number)
            checkValidatePattern(pattern)
            return Card(number to pattern)
        }

        private fun checkValidateNumber(number: String) {
            if (!NUMBERS.contains(number)) throw RuntimeException("올바르지 않은 카드번호입니다.")
        }

        private fun checkValidatePattern(pattern: Pattern) {
            if (!PATTERNS.contains(pattern)) throw RuntimeException("올바르지 않은 카드무늬입니다.")
        }
    }
}
