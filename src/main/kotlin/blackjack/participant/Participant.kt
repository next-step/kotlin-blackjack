package blackjack.participant

import blackjack.card.Card

abstract class Participant(
    open val name: PlayerName
) {
    abstract fun score(): Int
    abstract fun take(newCards: List<Card>)
}