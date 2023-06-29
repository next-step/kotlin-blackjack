package blackjack.domain.card

import blackjack.domain.score.CardScoreCalculator

data class Cards(
    val value: List<Card>,
) {
    val score = CardScoreCalculator.calculateScore(value)

    fun isBlackJack(): Boolean {
        return value.size == INIT_CARD_SIZE && score.isWinNumber
    }

    operator fun plus(card: Card): Cards {
        return Cards(value.plus(card))
    }

    companion object {

        const val INIT_CARD_SIZE = 2

        fun empty(): Cards {
            return Cards(emptyList())
        }

        fun initCards(cardDeck: CardDeck): Cards {
            return Cards(List(INIT_CARD_SIZE) { cardDeck.pick() })
        }
    }
}
