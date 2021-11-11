package blackjack.domain

import blackjack.view.input.ConsoleInputView.Companion.WRONG_PLAYER_NAME_MESSAGE

data class Player(val name: String) {
    private val cards: Cards = Cards()

    init {
        require(name.isNotBlank()) { WRONG_PLAYER_NAME_MESSAGE }
    }

    fun getCards(): List<Card> {
        return cards.cards.toList()
    }

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun getCardsString(): String {
        return cards.toString()
    }

    fun canReceiveAdditionalCard(): Boolean {
        return cards.canReceiveAdditionalCard()
    }

    fun getResult(): Int {
        return cards.getResult()
    }
}
