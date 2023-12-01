package blackjack_dealer.entity

import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber

class GamerCards {
    private var _trumpCard: MutableList<Card> = mutableListOf()
    val trumpCard get() = _trumpCard.toList()

    private val cardsContainACard: Boolean
        get() {
            return _trumpCard.any { card -> card.cardNumber == CardNumber.A }
        }

    private val sumOfCardNumbers: Int
        get() = _trumpCard.sumOf { it.cardNumber.number }

    fun getCurrentScore(): Int = if (cardsContainACard) {
        sumOfCardsWithA()
    } else {
        sumOfCardNumbers
    }

    fun getCurrentCards(): GamerCards {
        return _trumpCard.toGamerCards()
    }

    fun addCard(card: Card) {
        _trumpCard.add(card)
    }

    /**
     * A가 가질 수 있는 상태가 1, 11이지만 선택은 참여자가 선택한다고 생각해서 이곳에 함수를 만들었습니다.
     */
    private fun sumOfCardsWithA(): Int =
        if (sumOfCardNumbers + SUPER_A_PLUS_NUMBER > BLACK_JACK) {
            sumOfCardNumbers
        } else {
            sumOfCardNumbers + SUPER_A_PLUS_NUMBER
        }

    companion object {
        private const val BLACK_JACK = 21
        private const val SUPER_A_PLUS_NUMBER = 10

        fun newInstance(gamerCards: List<Card>): GamerCards {
            return GamerCards().apply {
                _trumpCard.addAll(gamerCards)
            }
        }
    }
}

fun List<Card>.toGamerCards() = GamerCards.newInstance(this)
