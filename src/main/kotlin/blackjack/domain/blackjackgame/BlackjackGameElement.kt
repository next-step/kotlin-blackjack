package blackjack.domain.blackjackgame

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.player.UserRole

class BlackjackGameElement(val gamers: List<UserRole>, private val deck: Deck) {

    init {
        validateMinPlayer(this.gamers.size)

        repeat(BASIC_RULE_COUNT) {
            this.gamers.forEach {
                it.draw(deck.deal())
            }
        }
    }

    private fun validateMinPlayer(playerCount: Int) {
        require(BLACKJACK_PLAY_MIN_PLAYER_COUNT > playerCount) { "플레이어가 너무 많습니다" }
    }

    fun draw(): Card = deck.deal()

    val cards: List<Card>
        get() = deck.cards


    companion object {
        private const val BASIC_RULE_COUNT = 2
        private const val BLACKJACK_CARD_COUNT = 52
        private const val BLACKJACK_PLAY_MIN_PLAYER_COUNT = BLACKJACK_CARD_COUNT / BASIC_RULE_COUNT
    }
}
