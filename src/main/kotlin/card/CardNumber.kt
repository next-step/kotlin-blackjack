package card

enum class CardNumber private constructor(private val point: Int, private val cardName: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    TREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    companion object {

        private val cardNumberList = listOf(
            ACE, TWO, TREE, FOUR, FIVE, SIX, SEVEN,
            EIGHT, NINE, TEN, JACK, QUEEN, KING,
        )

        fun CardNumber.getPoint(): Int {
            return this.point
        }

        fun CardNumber.getCardName(): String {
            return this.cardName
        }

        fun getCardNumberList() = cardNumberList
    }
}
