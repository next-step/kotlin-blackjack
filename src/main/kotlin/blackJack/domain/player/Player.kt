package blackJack.domain.player

import blackJack.domain.card.Cards
import blackJack.domain.enums.Status
import blackJack.domain.enums.Status.HIT
import blackJack.error.ErrorMessage

class Player(val name: String, cards: Cards = Cards(emptyList()), status: Status = HIT) : Participant(status, cards) {

    init {
        require(name.isNotEmpty()) { ErrorMessage.EMPTY_NAME.message }
    }

    fun continueGamePlayer(isContinue: Boolean, cardDeck: Cards) {
        if (isContinue) {
            val card = cardDeck.drawCard()
            addCard(card)
        } else {
            gameStop()
        }
    }

    fun gameStop() { status = Status.STAND }

    companion object {
        fun splitNames(inputNames: String): List<String> {
            return inputNames.split(",").map { it.trim() }.toList()
        }

        fun createPlayer(name: String): Player {
            return Player(name)
        }
    }
}
