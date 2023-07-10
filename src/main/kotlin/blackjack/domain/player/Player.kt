package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.deck.Deck
import blackjack.view.PlayerView

class Player(
    val name: String,
    val cards: MutableList<Card> = mutableListOf()
) {
    init {
        cards.firstOrNull { it.isAce() }?.apply { hasAce = true }
    }

    private var hasAce: Boolean = false
    private var isShow: Boolean = false
    private var isStand: Boolean = false
    private var isBust: Boolean = false

    fun drawStartHand(deck: Deck) {
        repeat(2) {
            cards.add(deck.getOneCard())
        }
    }

    fun hitOrStand(input: String, deck: Deck) {
        if (input == HIT_INPUT) {
            hit(deck)
            isShow = false
        }
        if (input == STAND_INPUT)
            stand()
    }

    private fun hit(deck: Deck) {
        val card = deck.getOneCard()
        if (card.isAce()) hasAce = true
        cards.add(card)
        if (checkBust()) bust()
    }

    private fun bust() {
        isBust = true
    }

    private fun stand() {
        isStand = true
    }

    fun canPlayable(): Boolean {
        return !isStand && !isBust
    }

    private fun checkBust(): Boolean {
        val total = cards.sumOf { it.getValue() }
        return total > BLACKJACK_NUMBER
    }

    fun getCardsValue(): Int {
        val total = cards.sumOf { it.getValue() }
        if (hasAce && total + ACE_HIDDEN_VALUE <= BLACKJACK_NUMBER) {
            return total + ACE_HIDDEN_VALUE
        }
        return total
    }

    fun printCards() {
        if (!isShow) {
            PlayerView.printPlayerCardsView(this)
            isShow = true
        }
    }

    companion object {
        const val HIT_INPUT = "y"
        const val STAND_INPUT = "n"
        const val BLACKJACK_NUMBER = 21
        const val ACE_HIDDEN_VALUE = 10
        fun of(name: String, deck: Deck): Player {
            return Player(name, cards = mutableListOf(deck.getOneCard(), deck.getOneCard()))
        }
    }
}
