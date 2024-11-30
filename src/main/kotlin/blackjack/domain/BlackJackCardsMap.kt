package blackjack.domain

class BlackJackCardsMap(
    private val cards: MutableList<BlackJackCard> = mutableListOf(),
    blackJackCardsSetter: BlackJackCardsSetter = RandomBlackJackCardsSetter(),
) {
    init {
        BlackJackCardShape.entries.flatMap(getBlackCardsPerShape()).forEach { cards.add(it) }
        blackJackCardsSetter.shuffle(cards)
    }

    fun get(): BlackJackCard {
        require(cards.isNotEmpty()) { "카드가 없어요" }
        val blackJackCard = cards.first()
        cards.removeFirst()
        return blackJackCard
    }

    private fun getBlackCardsPerShape() =
        { shape: BlackJackCardShape ->
            BlackJackCardNumber.entries.map { number ->
                BlackJackCard(
                    shape,
                    number,
                )
            }
        }
}
