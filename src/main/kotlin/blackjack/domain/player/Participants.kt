package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.Score

class Participants(
    val players: List<GamePlayer>,
    val dealer: Dealer
) {
    val allGameMembers: List<GameMember>
        get() = listOf(dealer) + players

    fun drawAll(deck: Deck) {
        dealer.drawCard(deck)
        players.forEach { it.drawCard(deck) }
    }

    fun getGameResult(): Map<String, Score> {
        val result = mutableMapOf<String, Score>(dealer.name to Score.init())
        val dealerScore = players.fold(Score.init()) { acc, player ->
            val score = dealer.compareScore(player)
            result[player.name] = score.reverse()
            acc + score
        }
        result[dealer.name] = dealerScore
        return result
    }
}
