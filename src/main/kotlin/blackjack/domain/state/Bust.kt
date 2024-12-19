package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Bust(hands: Hands) : Finished(hands){
    override val profitRate: Double = 0.0
}
