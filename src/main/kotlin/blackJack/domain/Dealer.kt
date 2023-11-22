package blackJack.domain

class Dealer {
    fun betting(): Cards {
        return Cards(List(INIT_CARD_COUNT) { Card.drawCard() })
    }

    fun addDrawCard(cards: Cards): Cards {
        val newCard = cards.drawUniqueCard()
        return Cards(cards.cards + newCard)
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
