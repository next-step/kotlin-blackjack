package blackjack.domain

interface CardDeck {
    fun getOne(): Card

    class Fake(private val card: Card) : CardDeck {
        override fun getOne(): Card {
            return card
        }
    }
}
