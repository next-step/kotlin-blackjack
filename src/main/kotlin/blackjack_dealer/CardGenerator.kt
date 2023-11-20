package blackjack_dealer

import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Cards

class CardGenerator(
    private val deque: CardDeque
) {
    fun generateSingleCard(): Card {
        return deque.cardDeque.removeLast()
    }

    fun generateDoubleCards(): Cards {
        val firstCard = deque.cardDeque.removeLast()
        val secondCard = deque.cardDeque.removeLast()
        val cardList = listOf(firstCard, secondCard)
        return Cards(cardList)
    }
}
