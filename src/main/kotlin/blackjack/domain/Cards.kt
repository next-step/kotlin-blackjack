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

    fun getBlackJackStatus(): BlackJackStatus {
        return when {
            score() == BLACK_JACK_SCORE && playerCards.size == FIRST_DISTRIBUTION_CARD_COUNT -> {
                BlackJackStatus.INIT_BLACK_JACK
            }
            score() == BLACK_JACK_SCORE -> {
                BlackJackStatus.BLACK_JACK
            }
            isHit() -> {
                BlackJackStatus.HIT
            }
            isBust() -> {
                BlackJackStatus.BUST
            }
            else -> BlackJackStatus.STAY
        }
    }

    fun score(): Int {
        val score = playerCards.sumOf { it.denomination.point }
        return if (playerCards.hasAceCard()) {
            compareContainedAceScore(score)
        } else {
            score
        }
    }

    private fun isBust(): Boolean {
        return score() > BLACK_JACK_SCORE
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
