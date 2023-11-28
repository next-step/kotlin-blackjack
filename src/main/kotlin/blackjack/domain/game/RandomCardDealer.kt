package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardSet

class RandomCardDealer : CardDealer {
    private val cards: List<Card> = Card.all().shuffled()
    private var index = 0

    override fun selectCard(): Card {
        require(index < cards.size) { "카드가 더 이상 존재하지 않습니다" }
        return cards[index++]
    }

    override fun selectCard(count: Int): CardSet {
        return CardSet(List(count) { selectCard() })
    }
}
