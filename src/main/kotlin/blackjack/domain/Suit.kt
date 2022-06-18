package blackjack.domain

enum class Suit(private val shape: String) {
    SPADE("♠"),
    DIAMOND("♢"),
    HEART("♡"),
    CLOVER("♣");

    override fun toString(): String {
        return shape
    }
}
