package blackJack.domain

import blackJack.error.ErrorMessage

class Player(val name: String, val cards: Cards) {

    init {
        require(name.isNotEmpty()) { ErrorMessage.EMPTY_NAME.message }
        require(cards.cards.isNotEmpty()) { ErrorMessage.EMPTY_CARD.message }
    }

    fun addCard(): Player {
        val dealer = Dealer()
        val newCards = dealer.addDrawCard(cards)
        return Player(name, newCards)
    }

    companion object {
        fun splitNames(inputNames: String): List<String> {
            return inputNames.split(",").map { it.trim() }.toList()
        }

        fun initBetting(name: String): Player {
            val dealer = Dealer()
            return Player(name, dealer.betting())
        }
    }
}
