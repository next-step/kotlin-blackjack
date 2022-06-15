package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.score.Score
import blackjack.dto.BlackJackRequest

class BlackJack(blackJackRequest: BlackJackRequest, private val cardDeck: CardDeck) {

    private val players = blackJackRequest.players
    private val dealer = blackJackRequest.dealer

    init {
        for (player in players) {
            repeat(FIRST_DEAL) { player.addCard(cardDeck.getOne()) }
        }

        repeat(FIRST_DEAL) {
            dealer.addCard(cardDeck.getOne())
        }
    }

    fun giveCard(player: Player) {
        player.addCard(cardDeck.getOne())
    }

    fun canHitPlayer(player: Player): Boolean {
        val score = Score.calculate(player)
        if (score <= PLAYER_MAX_HIT_SCORE) return true
        return false
    }

    fun giveCardToDealer() {
        if (canHitDealer()) {
            giveCard(dealer)
        }
    }

    private fun canHitDealer(): Boolean {
        val score = Score.calculate(dealer)
        if (score <= DEALER_MAX_HIT_SCORE) return true
        return false
    }

    companion object {
        const val FIRST_DEAL = 2
        const val DEALER_MAX_HIT_SCORE = 16
        const val PLAYER_MAX_HIT_SCORE = 20
    }
}
