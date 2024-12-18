package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Blackjack(hands: List<PlayingCard>) : State {
    override val cards: List<PlayingCard>
        get() = TODO("Not yet implemented")

}
