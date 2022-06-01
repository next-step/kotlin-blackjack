package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.Score.Companion.BLACK_JACK
import blackjack.domain.card.Card
import blackjack.domain.card.type.Ace

class CardsInHand(
    private val cards: List<Card>
) {
    fun calculateScore(): Score
    = cards.map { it.denomination }
        .sortedDescending()
        .fold(Score(0)) { acc, denomination ->
            if (denomination is Ace && acc.plus(denomination.aceScore).isLessThan(BLACK_JACK)) {
                acc + denomination.aceScore
            } else {
                acc + denomination.score
            }
        }
}
