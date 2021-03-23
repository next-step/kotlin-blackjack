package blackjack.card

enum class CardValue(val desc: String, val value: Int, val otherValue: Int, val order: Int) {
    ONE("A", 1, 11, 2),
    TWO("2", 2, 2, 1),
    THREE("3", 3, 3, 1),
    FOUR("4", 4, 4, 1),
    FIVE("5", 5, 5, 1),
    SIX("6", 6, 6, 1),
    SEVEN("7", 7, 7, 1),
    EIGHT("8", 8, 8, 1),
    NINE("9", 9, 9, 1),
    KING("K", 10, 10, 1),
    QUEEN("Q", 10, 10, 1),
    JACK("J", 10, 10, 1);

    fun hasOtherValue(): Boolean {
        return this.otherValue > 0
    }
}
