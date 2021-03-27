package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

object StateFactory {

    fun init(cardPair: Pair<Card, Card>): State {
        require(cardPair.first != cardPair.second) { "동일한 카드로는 생성할 수 없습니다. first: ${cardPair.first}, second: ${cardPair.second}" }

        val cards = Cards(cardPair.toList())
        val score = cards.score

        if (score == Score.BLACKJACK) {
            return Blackjack(cards)
        }

        return Hit(cards)
    }
}
