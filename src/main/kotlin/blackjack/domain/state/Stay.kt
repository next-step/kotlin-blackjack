package blackjack.domain.state

class Stay(hands: Hands) : Finished(hands) {
    override val profitRate: Double = 1.0
}
