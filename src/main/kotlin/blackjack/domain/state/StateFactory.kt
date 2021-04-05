package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

object StateFactory {

    fun init(firstCard: Card, secondCard: Card): State {
        require(firstCard != secondCard) { "동일한 카드로는 생성할 수 없습니다. first: $firstCard, second: $secondCard" }

        val cards = Cards(listOf(firstCard, secondCard))
        val score = cards.score

        if (score == Score.BLACKJACK) {
            return Blackjack(cards)
        }

        return Hit(cards)
    }
}
