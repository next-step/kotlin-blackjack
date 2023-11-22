package blackJack.domain

import blackJack.domain.Answer.*
import blackJack.domain.Status.*
import blackJack.error.ErrorMessage

class Player(val name: String, val cards: Cards, private var status: Status) {

    init {
        require(name.isNotEmpty()) { ErrorMessage.EMPTY_NAME.message }
    }

    fun isHit(): Boolean = status == HIT

    fun addCard(dealer: Dealer, answer: Answer) {
        if (answer == n) return

        Status.addCardValidation(status)
        val card = dealer.cardDeck.addCard()
        cards.cards.add(card)
        status = Status.calculateStatus(cards.calculateTotalScore(), answer)
    }

    companion object {
        fun splitNames(inputNames: String): List<String> {
            return inputNames.split(",").map { it.trim() }.toList()
        }

        fun createPlayer(name: String, dealer: Dealer): Player {
            val cards = dealer.initialCards()
            val score = cards.calculateTotalScore()
            return Player(name, cards, Status.calculateStatus(score))
        }
    }
}
