package blackjack.domain

data class Participant(
    val name: String,
    private val cardDeck: Deck
) {
    private val _playerCards = mutableListOf<Card>()

    val cards: List<Card>
        get() = _playerCards.toList()

    fun score(): Int {
        val score = cards.sumOf { it.denomination.point }
        return if (cards.hasAceCard()) {
            compareContainedAceScore(score)
        } else {
            score
        }
    }

    private fun compareContainedAceScore(score: Int): Int {
        return if (score < BIG_VALUE_OF_ACE) {
            score + (BIG_VALUE_OF_ACE - Card.Denomination.ACE.point)
        } else score
    }

    fun addCard() {
        _playerCards.add(cardDeck.draw())
    }

    fun addFirstCard() {
        repeat(FIRST_DISTRIBUTION_CARD_COUNT) { _playerCards.add(cardDeck.draw()) }
    }

    private fun List<Card>.hasAceCard(): Boolean {
        return this.map { it.denomination }.contains(Card.Denomination.ACE)
    }

    companion object {
        fun of(playerName: String, cardDeck: Deck): Participant {
            return Participant(playerName, cardDeck = cardDeck)
        }

        private const val BIG_VALUE_OF_ACE = 11
        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
