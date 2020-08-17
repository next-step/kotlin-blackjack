package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.result.GameResult
import blackjack.domain.result.PlayerResult
import blackjack.domain.result.PlayerResults

data class Dealer(
    override val info: PlayerInfo = PlayerInfo(NAME, 0),
    override val cards: Cards = Cards.empty()
) : Player {

    override fun deal(deck: Deck): Player {
        validateDealCallOnce()
        return copy(cards = this.cards + deck.getDealCards())
    }

    override fun hit(deck: Deck): Player {
        validateCanHit()
        return copy(cards = this.cards + deck.fetchCard())
    }

    override fun canPlay() = cards.canPlay() && cards.totalScore < DEALER_STOP_SCORE

    fun createPlayerResults(players: List<Player>): PlayerResults {
        return players.map { GameResult.ofChallenger(this, it) }
            .zip(players) { result, player -> PlayerResult(player.getName(), result.earnMoney(player)) }
            .let { PlayerResults(it) }
    }

    companion object {
        const val NAME = "딜러"
        private const val DEALER_STOP_SCORE = 17
    }
}
