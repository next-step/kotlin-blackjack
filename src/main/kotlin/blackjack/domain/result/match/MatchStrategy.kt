package blackjack.domain.result.match

import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer

interface MatchStrategy {
    fun match(dealer: Dealer, player: GamePlayer): MatchState
}
