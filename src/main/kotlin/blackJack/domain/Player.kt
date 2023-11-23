package blackJack.domain

import blackJack.domain.Status.HIT
import blackJack.error.ErrorMessage

class Player(val name: String, val cards: Cards = Cards(mutableListOf()), var status: Status = HIT) {

    init {
        require(name.isNotEmpty()) { ErrorMessage.EMPTY_NAME.message }
    }

    fun addCard(cardDeck: Cards) {
        Status.validationAddCard(status)
        val card = cardDeck.drawCard()
        cards.addCard(card)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    fun receiveInitialCards(initialCards: Cards) {
        cards.addCard(initialCards.drawCard())
        cards.addCard(initialCards.drawCard())
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    fun isContinued(answer: String) = answer == "y"

    fun getTotalScore(): Int = cards.calculateTotalScore()

    companion object {
        fun splitNames(inputNames: String): List<String> {
            return inputNames.split(",").map { it.trim() }.toList()
        }

        fun createPlayer(name: String): Player {
            return Player(name)
        }
    }
}
