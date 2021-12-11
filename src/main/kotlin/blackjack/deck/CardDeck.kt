package blackjack.deck

import blackjack.entity.Card
import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit

object CardDeck {

    private val _cards = mutableListOf<Card>()
    val cards get() = _cards

    init {
        Suit
            .values()
            .forEach { suit ->
                Denomination
                    .values()
                    .forEach { denomination ->
                        cards.add(Card(suit, denomination))
                    }
            }

        _cards.shuffle()
    }

    fun draw(): Card {
        require(cards.isNotEmpty()) { "더이상 발급 받을 수 있는 카드가 없습니다." }

        val card = _cards.random()
        _cards.remove(card)

        return card
    }

}
