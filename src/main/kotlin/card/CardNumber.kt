package card

enum class CardNumber private constructor(private val point: Int) {
    ACE(1),
    TWO(2),
    TREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    companion object {

        private val cardNumberList = listOf(
            ACE, TWO, TREE, FOUR, FIVE, SIX, SEVEN,
            EIGHT, NINE, TEN, JACK, QUEEN, KING,
        )

        fun CardNumber.getPoint(): Int {
            return this.point
        }

        fun getCardNumberList() = cardNumberList
    }
}
