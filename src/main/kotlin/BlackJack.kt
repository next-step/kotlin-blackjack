class BlackJack(
    private val players: List<Player>,
)

class Player(
    val name: String,
    private val hands: Hands,
)

class Hands(
    val cards: MutableList<Card>,
)

enum class Card(
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