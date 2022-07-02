package blackjack.domain.blackjackgame

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Decks
import blackjack.domain.player.UserRole

class BlackjackGameElement(val gamers: List<UserRole>, private val decks: Decks) {

    private var deck: Deck = decks.getDeck()

    init {

        repeat(BASIC_RULE_COUNT) {
            this.gamers.forEach {
                checkCardStatus()
                it.draw(deck.deal())
            }
        }
    }

    private fun checkCardStatus() {
        if (deck.isEmptyCard()) deck = decks.getDeck()
    }

    fun draw(): Card = deck.deal()

    companion object {
        private const val BASIC_RULE_COUNT = 2
        private const val BLACKJACK_CARD_COUNT = 52
        private const val BLACKJACK_PLAY_MIN_PLAYER_COUNT = BLACKJACK_CARD_COUNT / BASIC_RULE_COUNT
    }
}
