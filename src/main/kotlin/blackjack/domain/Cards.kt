package blackjack.domain

class Cards(
    cards: List<Card> = emptyList()
) {
    val playerCards: List<Card>
        get() = _playerCards.toList()

    private val _playerCards = cards.toMutableList()
    fun addCard(card: Card) {
        _playerCards.add(card)
    }

    fun score(): Int {
        val score = playerCards.sumOf { it.denomination.point }
        return if (playerCards.hasAceCard()) {
            compareContainedAceScore(score)
        } else {
            score
        }
    }

    private fun List<Card>.hasAceCard(): Boolean {
        return this.map { it.denomination }.contains(Card.Denomination.ACE)
    }

    private fun compareContainedAceScore(score: Int): Int {
        return if (score <= BIG_VALUE_OF_ACE) {
            score + (BIG_VALUE_OF_ACE - Card.Denomination.ACE.point)
        } else {
            score
        }
    }

    companion object {
        private const val BIG_VALUE_OF_ACE = 11
    }
}
