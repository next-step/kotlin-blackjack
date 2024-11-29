package blackjack.domain

class BlackJackCardsMap(val cardsMap: MutableMap<BlackJackCard, Boolean> = mutableMapOf()) {
    fun get(
        shape: BlackJackCardShape = RandomBlackJackCardShapeGenerator().getShape(),
        number: BlackJackCardNumber = RandomBlackJackCardNumberGenerator().getNumber(),
    ): BlackJackCard {
        if (cardsMap.get(BlackJackCard(shape, number)) == true) {
            require(!isMapFull()) { " 더이상 카드를 줄 수 없어요 " }
            return get(RandomBlackJackCardShapeGenerator().getShape(), RandomBlackJackCardNumberGenerator().getNumber())
        }
        cardsMap.put(BlackJackCard(shape, number), true)
        return BlackJackCard(shape, number)
    }

    fun isMapFull(): Boolean {
        val blackJackCards =
            BlackJackCardShape.entries.flatMap(getAllBlackJackCards())
        return blackJackCards.all { cardsMap[it] == true }
    }

    private fun getAllBlackJackCards() =
        { shape: BlackJackCardShape ->
            BlackJackCardNumber.entries.map { number ->
                BlackJackCard(
                    shape,
                    number,
                )
            }
        }
}
