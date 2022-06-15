package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.dto.BlackJackRequest

class BlackJack(blackJackRequest: BlackJackRequest, private val cardDeck: CardDeck) {

    private val players = blackJackRequest.players
    private val dealer = blackJackRequest.dealer

    init {
        println(dealer.cards)
        println(dealer.name)
        for (player in players) {
            repeat(FIRST_DEAL) { player.addCard(cardDeck.getOne()) }
        }

        repeat(FIRST_DEAL) {
            println(dealer.cards)
            dealer.addCard(cardDeck.getOne())
        }
    }

    fun giveCard(player: Player) {
        player.addCard(cardDeck.getOne())
    }

    companion object {
        const val FIRST_DEAL = 2
    }
}
