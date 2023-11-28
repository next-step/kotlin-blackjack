package blackJack.domain.player

import blackJack.domain.card.Cards
import blackJack.domain.enums.Status
import blackJack.domain.enums.Status.*

class Dealer(val name: String, cards: Cards = Cards(emptyList()), status: Status = HIT) : Participant(status, cards) {

    override fun isContinued(): Boolean {
        val totalScore = cards.calculateTotalScore()
        return status == HIT && totalScore < 17
    }

    companion object {
        private const val DEALER_NAME = "딜러"

        fun createDealer(): Dealer {
            return Dealer(DEALER_NAME)
        }
    }
}
