package blackjack.domain.blackjack

import blackjack.domain.card.Cards
import blackjack.domain.common.Money
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Players

data class BlackJackResult(
    private val dealer: Dealer,
    private val players: Players
) {

    fun dealerResult(): ParticipantResult {
        return ParticipantResult.of(dealer)
    }

    fun playersResult(): List<ParticipantResult> {
        return players.players.map { ParticipantResult.of(it) }
    }

    fun dealerProfit(): ParticipantProfitResult {
        val profitByPlayer = players.profit(dealer)
        val profit = profitByPlayer.values.fold(Money.ZERO) { acc, playerProfit -> acc.minus(playerProfit) }
        return ParticipantProfitResult(dealer.name, profit)
    }

    fun playersProfit(): List<ParticipantProfitResult> {
        val profitByPlayer = players.profit(dealer)
        return profitByPlayer.map { (player, profit) -> ParticipantProfitResult(player.name, profit) }
    }
}

data class ParticipantResult(
    val name: String,
    val cards: Cards,
) {
    companion object {
        fun of(participant: Participant): ParticipantResult {
            return ParticipantResult(
                name = participant.name,
                cards = participant.cards,
            )
        }
    }
}

data class ParticipantProfitResult(
    val name: String,
    val profit: Money
)
