package blackJack.domain

import blackJack.domain.Answer.*
import blackJack.domain.Status.*
import blackJack.error.ErrorMessage

class Player(val name: String, val cards: Cards, private var status: Status) {

    init {
        require(name.isNotEmpty()) { ErrorMessage.EMPTY_NAME.message }
    }

    fun isHit(): Boolean = status == HIT

    fun addCard(dealer: Dealer, answer: String) {
        if (checkAnswer(answer)) return

        Status.addCardValidation(status)
        val card = dealer.cardDeck.drawCard()
        cards.addCard(card)
        status = Status.calculateStatus(cards.calculateTotalScore(), Answer.valueOf(answer))
    }

    private fun checkAnswer(answer: String): Boolean {
        Answer.validateAnswer(answer)
        val answerValue = Answer.valueOf(answer)
        return answerValue == n
    }

//    fun playGame(dealer: Dealer): Player {
//    }

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
