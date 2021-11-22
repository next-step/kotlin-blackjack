package blackjack.ui

import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.strategy.ui.output.OutputStrategy

class ResultView(private val outputStrategy: OutputStrategy) {

    private fun denominationName(denomination: Denomination): String {
        return when (denomination) {
            Denomination.ACE -> "A"
            Denomination.TWO -> "2"
            Denomination.THREE -> "3"
            Denomination.FOUR -> "4"
            Denomination.FIVE -> "5"
            Denomination.SIX -> "6"
            Denomination.SEVEN -> "7"
            Denomination.EIGHT -> "8"
            Denomination.NINE -> "9"
            Denomination.TEN -> "10"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
        }
    }

    private fun suitName(suit: Suit): String {
        return when (suit) {
            Suit.CLUB -> "클럽"
            Suit.DIAMOND -> "다이아몬드"
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
        }
    }

    companion object {
        private const val THE_RESULT_MESSAGE_OF_HANDING_OUT_CARDS = "%s에게 2장의 카드를 나누었습니다."
        private const val CARDS_HELD_BY_THE_PARTICIPANT_INFORMATIONAL_MESSAGE = "%s카드: %s"
        private const val RESULT_MESSAGE = "%s카드: %s - 결과: %s"
    }
}
