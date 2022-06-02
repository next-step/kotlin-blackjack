package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.Score.Companion.BLACK_JACK
import blackjack.domain.card.Card
import blackjack.domain.card.type.Ace

class CardsInHand(
    cards: List<Card>
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun calculateScore(): Score = _cards.map { it.denomination }
        .sortedDescending()
        .fold(Score(0)) { acc, denomination ->
            if (denomination is Ace && acc.plus(denomination.aceScore).isLessThan(BLACK_JACK)) {
                acc + denomination.aceScore
            } else {
                acc + denomination.score
            }
        }

    fun add(card: Card) {
        _cards.add(card)
    }
}
