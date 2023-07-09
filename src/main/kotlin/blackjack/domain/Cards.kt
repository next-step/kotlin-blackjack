package blackjack.domain

class Cards private constructor(
    val list: List<Card>
) {

    companion object {
        fun of(cards: List<Card>): Cards {
            return Cards(cards.shuffled())
        }
    }
}
