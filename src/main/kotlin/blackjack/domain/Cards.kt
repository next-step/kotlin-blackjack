package blackjack.domain

abstract class Cards(
    cards: List<Card> = emptyList()
) {
    val playerCards: List<Card>
        get() = _playerCards.toList()

    private val _playerCards = cards.toMutableList()

    abstract fun isHit(): Boolean

    fun addCard(card: Card) {
        _playerCards.add(card)
    }

    fun initContributionCardIsBlackJack(): Boolean {
        return score() == BLACK_JACK_SCORE && playerCards.size == FIRST_DISTRIBUTION_CARD_COUNT
    }

    fun score(): Int {
        val score = playerCards.sumOf { it.denomination.point }
        return if (playerCards.hasAceCard()) {
            compareContainedAceScore(score)
        } else {
            score
        }
    }

    fun isBust(): Boolean {
        return score() >= BLACK_JACK_SCORE
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
        private const val BLACK_JACK_SCORE = 21
        private const val BIG_VALUE_OF_ACE = 11
        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
