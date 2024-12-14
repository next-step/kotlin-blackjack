package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards

data class Player(
    private val name: PlayerName,
    var cards: Cards = Cards(),
) : Participant(name) {
    // TODO: Player가 받을지 결정하도록 변경
    fun isBust(): Boolean {
        return cards.isBust()
    }

    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    fun score(): Int {
        return cards.bestScore()
    }

    override fun toString(): String {
        return "${name.value}카드: $cards"
    }
}
