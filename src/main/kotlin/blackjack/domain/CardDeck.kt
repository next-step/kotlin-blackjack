package blackjack.domain

interface CardDeck {
    fun getOne(): Card

    class Fake(private val cards: MutableList<Card>) : CardDeck {
        override fun getOne(): Card {
            return cards.removeFirst()
        }
    }
}
