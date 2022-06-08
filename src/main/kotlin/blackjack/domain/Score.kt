package blackjack.domain

import blackjack.domain.CardDeckImpl.Companion.ACE
import blackjack.domain.CardDeckImpl.Companion.JACK
import blackjack.domain.CardDeckImpl.Companion.KING
import blackjack.domain.CardDeckImpl.Companion.QUEEN

class Score(private val players: List<Player>) {
    private var _playerScores: MutableList<PlayerScore> = mutableListOf()
    val playerScore: List<PlayerScore> = _playerScores

    fun run() {
        players.forEach { player ->
            val sum = sum(player.cards)
            _playerScores.add(PlayerScore(player, sum))
        }
    }

    private fun sum(cards: List<Card>): Int {
        var sum = 0
        cards.forEach { card ->
            sum += when (card.number) {
                ACE -> 11
                QUEEN -> 10
                JACK -> 10
                KING -> 10
                else -> card.number.toInt()
            }
        }

        if (isBurst(sum) && containAce(cards)) {
            sum -= 10
        }

        return sum
    }

    private fun isBurst(score: Int): Boolean {
        if (score > BLACK_JACK) return true
        return false
    }

    private fun containAce(cards: List<Card>): Boolean {
        return cards.any { it.number === ACE }
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
