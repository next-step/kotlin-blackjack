package blackjack.domain.state

class Blackjack(hands: Hands) : Finished(hands){
    override val profitRate: Double = 1.5
}
