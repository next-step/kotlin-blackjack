package blackJack.domain

import blackJack.domain.Status.HIT
import blackJack.error.ErrorMessage

class Player(val name: String, val cards: Cards = Cards(mutableListOf()), var status: Status = HIT) {

    init {
        require(name.isNotEmpty()) { ErrorMessage.EMPTY_NAME.message }
    }

    fun addCard(card: Card) {
        Status.validationAddCard(status)
        cards.addCard(card)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    fun receiveInitialCards(initialCards: Cards) {
        cards.addAllCard(initialCards)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    fun isContinued() = status == HIT

    fun getTotalScore(): Int = cards.calculateTotalScore()

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
