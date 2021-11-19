package blackJack.domain

class Cards(private val cards: List<Card>) : List<Card> by cards {

    operator fun plus(card: Card): Cards {
        checkDuplicate(card)
        return Cards(cards + card)
    }

    operator fun minus(card: Card): Cards {
        return Cards(cards - card)
    }

    private fun checkDuplicate(card: Card) {
        require(card !in cards) { DUPLICATE_ERROR }
    }

    fun sumCards(sum: Int = 0): Int {
        return cards.sumOf { it.denomination.score(sum) }
    }

    fun drawRandomCard() = cards.random()

    companion object {
        fun create(): Cards = Cards(makeCards())

        private fun makeCards(): List<Card> = suits.map { suit ->
            denominations.map { denomination -> Card(suit, denomination) }
        }.flatten()

        private const val DUPLICATE_ERROR = "중복 된 카드가 있습니다."
        private val denominations = Denomination.values()
        private val suits = Suit.values()
    }
}
