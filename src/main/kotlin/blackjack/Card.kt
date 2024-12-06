package blackjack

class Card(
    private val suit: CardSuit,
    private val number: CardNumber,
) {
    fun value(calculatedValue: Int): Int = number.value(calculatedValue)

    fun toDrawCard(): DrawCard = DrawCard(suit, number)
}

data class DrawCard(
    val suit: CardSuit,
    val number: CardNumber,
) {
    val drawCardString = "${convertNumber(number)}${convertSuit(suit)}"

    private fun convertSuit(suit: CardSuit): String =
        when(suit) {
            CardSuit.HEARTS -> "하트"
            CardSuit.DIAMONDS -> "다이아몬드"
            CardSuit.CLUBS -> "클로버"
            CardSuit.SPADES -> "스페이드"
        }

    private fun convertNumber(number: CardNumber): String =
        when(number) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }
}

enum class CardSuit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES,
}

enum class CardNumber(
    private val decideCardValue: (Int) -> Int,
) {
    ACE({
        calculatedValue ->
        if (calculatedValue < 11) {
            11
        } else {
            1
        }
    }),
    TWO({ 2 }),
    THREE({ 3 }),
    FOUR({ 4 }),
    FIVE({ 5 }),
    SIX({ 6 }),
    SEVEN({ 7 }),
    EIGHT({ 8 }),
    NINE({ 9 }),
    TEN({ 10 }),
    JACK({ 10 }),
    QUEEN({ 10 }),
    KING({ 10 });

    fun value(calculatedValue: Int): Int = decideCardValue(calculatedValue)
}