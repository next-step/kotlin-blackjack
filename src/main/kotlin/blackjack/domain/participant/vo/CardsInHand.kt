package blackjack.domain.participant.vo

import blackjack.domain.Score
import blackjack.domain.Score.Companion.BLACKJACK
import blackjack.domain.card.Card
import blackjack.domain.card.type.Ace

class CardsInHand(
    cards: List<Card> = emptyList()
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun calculateScore(): Score = _cards.map { it.denomination }
        .sortedDescending()
        .fold(Score.zero()) { acc, denomination ->
            if (denomination is Ace && acc.plus(denomination.aceScore) <= BLACKJACK) {
                acc + denomination.aceScore
            } else {
                acc + denomination.score
            }
        }

    fun add(card: Card): Boolean = _cards.add(card)
}
