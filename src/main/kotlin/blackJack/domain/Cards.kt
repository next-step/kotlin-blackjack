package blackJack.domain

@JvmInline
value class Cards(val cards: List<Card>) {

    fun getSize(): Int = cards.size

    companion object {
        private val denominations = Denomination.values()
        private val suits = Suit.values()

        fun create(): Cards = Cards(makeCards())

        private fun makeCards(): List<Card> = suits.map { suit ->
            denominations.map { denomination -> Card(suit, denomination) }
        }.flatten()
    }
}
