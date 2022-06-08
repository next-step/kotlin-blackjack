package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.dto.BlackJackRequest

class BlackJack(dto: BlackJackRequest, private val cardDeck: CardDeck) {

    private val players = dto.players

    init {
        for (player in players) {
            repeat(FIRST_TURN) { player.addCard(cardDeck.getOne()) }
        }
    }

    fun giveCard(player: Player) {
        player.addCard(cardDeck.getOne())
    }

    companion object {
        const val FIRST_TURN = 2
    }
}
