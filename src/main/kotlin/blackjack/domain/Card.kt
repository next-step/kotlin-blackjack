package blackjack.domain

class Card(
    private val suit: CardSuit,
    private val number: CardNumber,
) {
    fun value(calculatedValue: Int): Int = number.value(calculatedValue)

    fun isAce(): Boolean = number == CardNumber.ACE

    fun toDrawCard(): DrawCard = DrawCard(suit, number)
}

data class DrawCard(
    val suit: CardSuit,
    val number: CardNumber,
)

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
