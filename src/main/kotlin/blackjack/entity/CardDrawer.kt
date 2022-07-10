package blackjack.entity

object CardDrawer {
    fun drawSingleCard(): Card {
        return Deck.cards.removeFirst()
    }

    fun drawInitialCards(): Hands {
        return Hands(
            mutableListOf<Card>().apply {
                repeat(2) { this.add(drawSingleCard()) }
            }.toList()
        )
    }

    fun drawAdditionalCard(cardList: List<Card>): Hands {
        val cards = cardList.toMutableList()
        cards.add(drawSingleCard())
        return Hands(cards)
    }

    fun getRandomShape(): Shape {
        return Shape.values().toList().shuffled().first()
    }

    fun getRandomNumber(): CardNumber {
        return CardNumber.values().toList().shuffled().first()
    }
}
