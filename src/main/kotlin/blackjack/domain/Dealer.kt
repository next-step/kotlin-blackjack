package blackjack.domain

class Dealer(
    override val name: String = "딜러",
    override val hands: Hands = Hands(),
) : Participant(name, hands)
