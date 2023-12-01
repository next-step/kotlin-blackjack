package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.rule.MatchedProfitRule

object Match {

    fun applyAllResult(dealer: Dealer, participants: List<Participant>) {
        participants.forEach { it.profit(dealer, MatchedProfitRule()) }
        dealer.profit = (-1 * participants.sumOf { it.profit })
    }
}
