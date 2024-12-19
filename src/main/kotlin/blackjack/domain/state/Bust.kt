package blackjack.domain.state

class Bust(hands: Hands) : Finished(hands) {
    override val profitRate: Double = 0.0
}
