package blackjack.domain

data class Participant(
    val name: String
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

    fun addCard(card: Card) {
        _playerCards.add(card)
    }

    private fun List<Card>.hasAceCard(): Boolean {
        return this.map { it.denomination }.contains(Card.Denomination.ACE)
    }

    companion object {
        fun of(playerName: String): Participant {
            return Participant(playerName)
        }

        private const val BIG_VALUE_OF_ACE = 11
    }
}
