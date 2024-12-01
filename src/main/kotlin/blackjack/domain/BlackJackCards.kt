package blackjack.domain

import java.util.Stack

class BlackJackCards(cards: List<BlackJackCard>) {
    val cardsStack: Stack<BlackJackCard> = Stack()

    init {
        cards.forEach { cardsStack.push(it) }
    }

    fun draw(): BlackJackCard {
        require(cardsStack.isNotEmpty()) { "카드가 없어요" }
        val blackJackCard = cardsStack.pop()
        return blackJackCard
    }

    companion object {
        fun getDefaultCards(): BlackJackCards {
            val cards: MutableList<BlackJackCard> = mutableListOf()
            BlackJackCardShape.entries.flatMap(getBlackCardsPerShape()).shuffled().forEach { cards.add(it) }
            return BlackJackCards(cards)
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
}
