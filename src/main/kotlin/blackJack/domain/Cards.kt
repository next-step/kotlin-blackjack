package blackJack.domain

@JvmInline
value class Cards(val cards: List<Card>) {

    operator fun plus(card: Card): Cards = Cards(cards + card)

    fun getSize(): Int = cards.size

    fun checkOverlap(card: Card) {
        require(card in cards) { "중복 된 카드가 있습니다." }
    }

    companion object {
        private val denominations = Denomination.values()
        private val suits = Suit.values()

        fun create(): Cards = Cards(makeCards())

        private fun makeCards(): List<Card> = suits.map { suit ->
            denominations.map { denomination -> Card(suit, denomination) }
        }.flatten()
    }
}
