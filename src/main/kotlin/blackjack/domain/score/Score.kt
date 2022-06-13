package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck.Companion.ACE
import blackjack.domain.card.RandomCardDeck.Companion.JACK
import blackjack.domain.card.RandomCardDeck.Companion.KING
import blackjack.domain.card.RandomCardDeck.Companion.QUEEN
import blackjack.domain.player.Player

class Score {
    private var _playerScores: MutableList<PlayerScore> = mutableListOf()
    val playerScore: List<PlayerScore>
        get() = _playerScores.toList()

    fun calculate(players: List<Player>) {
        players.forEach { player ->
            val sum = sum(player.cards)
            _playerScores.add(PlayerScore(player, sum))
        }
    }

    private fun sum(cards: List<Card>): Int {
        var sum = 0
        cards.forEach { card ->
            sum += when (card.number) {
                ACE -> ACE_DEFAULT_SCORE
                QUEEN -> QUEEN_SCORE
                JACK -> JACK_SCORE
                KING -> KING_SCORE
                else -> card.number.toInt()
            }
        }

        if (isBurst(sum) && containAce(cards)) {
            sum = sum - ACE_DEFAULT_SCORE + ACE_SCORE
        }

        return sum
    }

    private fun isBurst(score: Int): Boolean {
        if (score > BLACK_JACK_SCORE) return true
        return false
    }

    private fun containAce(cards: List<Card>): Boolean {
        return cards.any { it.number == ACE }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val ACE_DEFAULT_SCORE = 11
        private const val ACE_SCORE = 1
        private const val JACK_SCORE = 10
        private const val QUEEN_SCORE = 10
        private const val KING_SCORE = 10
    }
}
