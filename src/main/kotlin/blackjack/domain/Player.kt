package blackjack.domain

import blackjack.view.input.ConsoleInputView.Companion.WRONG_PLAYER_NAME_MESSAGE

data class Player(val name: String) {
    private val cardsHandler = PlayerCardsHandler()

    init {
        require(name.isNotBlank()) { WRONG_PLAYER_NAME_MESSAGE }
    }

    fun getCards(): List<Card> {
        return cardsHandler.getCards()
    }

    fun receiveCard(card: Card) {
        cardsHandler.addCard(card)
    }

    fun getCardsString(): String {
        return cardsHandler.getCardsString()
    }

    fun canReceiveAdditionalCard(): Boolean {
        return cardsHandler.canReceiveAdditionalCard()
    }

    fun getCardsResultPoint(): Int {
        return cardsHandler.getCardsResultPoint()
    }
}
