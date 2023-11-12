package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Deck

class Dealer {

    private val cardSet = Deck.newDeck()
    private var pointer = 0

    fun dealingTwoCards(): List<Card> {
        require(pointer + 2 < cardSet.size) { "카드가 부족합니다." }
        return cardSet
            .subList(pointer, pointer + 2)
            .apply { pointer += 2 }
    }

    fun dealingOneCard(): Card {
        require(pointer + 1 < cardSet.size) { "카드가 부족합니다." }
        return cardSet[pointer++]
    }
}
