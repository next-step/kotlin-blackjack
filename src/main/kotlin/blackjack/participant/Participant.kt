package blackjack.participant

import blackjack.card.Card
import blackjack.card.DefaultCardHolder

abstract class Participant(
    private val name: PlayerName,
    private val holder: DefaultCardHolder,
) {
    fun getName(): PlayerName {
        return name
    }

    fun score(): Int {
        return holder.score()
    }

    fun take(newCard: Card) {
        holder.take(newCard)
    }

    fun take(newCards: List<Card>) {
        holder.take(newCards)
    }
}
