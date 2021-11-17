package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck

class Dealer(
    val dealer: Participant
) : CardFunction by dealer {

    private val resultStatuses = mutableListOf<ResultStatus>()

    fun addCardWhenLessThanStandard(
        cardsDeck: CardsDeck,
    ): Card? {
        if (dealer.getCardSum() >= STANDARD) {
            return null
        }

        val card = cardsDeck.divide()
        addCard(card)

        return card
    }

    fun determineWinOrLose(resultStatus: ResultStatus) {
        resultStatuses.add(resultStatus)
    }

    fun getMatchResult(): Map<ResultStatus, Int> {
        return resultStatuses
            .groupingBy { it }
            .eachCount()
    }

    companion object {
        private const val STANDARD = 17
    }
}
