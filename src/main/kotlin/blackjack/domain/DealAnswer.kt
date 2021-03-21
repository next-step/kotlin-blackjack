package blackjack.domain

enum class DealAnswer {
    YES, NO;

    fun isYes(): Boolean {
        return this == YES
    }
}
