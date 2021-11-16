package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck

class Dealer(
    val dealer: Participant
) : CardFunction by dealer {

    private val _resultStatuses = mutableListOf<ResultStatus>()
    val resultStatuses: List<ResultStatus>
        get() {
            return _resultStatuses.toList()
        }

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
        _resultStatuses.add(resultStatus)
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
