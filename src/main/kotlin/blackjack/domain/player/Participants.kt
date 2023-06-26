package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.Score

class Participants(
    val players: List<PlayerImpl>,
    val dealer: Dealer
) {
    val allPlayers: List<Player>
        get() = listOf(dealer) + players

    fun drawAll(deck: Deck) {
        dealer.drawCard(deck)
        players.forEach { it.drawCard(deck) }
    }

    fun getGameResult(): Map<String, Score> {
        val result = mutableMapOf<String, Score>(dealer.name to Score(0, 0, 0))
        val dealerScore = players.fold(Score(0, 0, 0)) { acc, player ->
            val score = dealer.compareScore(player)
            result[player.name] = score.reverse()
            acc + score
        }
        result[dealer.name] = dealerScore
        return result
    }
}
