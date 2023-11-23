package blackjack.domain

object Dealer : Participant() {
    override val name: String = "딜러"
    override val cards: Cards = Cards()
}
