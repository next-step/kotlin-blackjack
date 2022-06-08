package blackjack.domain

import blackjack.dto.BlackJackRequest

class BlackJack(dto: BlackJackRequest, private val cardDeck: CardDeck) {

    private val players = dto.players

    init {
        for (player in players) {
            repeat(FIRST_TURN) { player.addCard(cardDeck.getOne()) }
        }
    }

    companion object {
        const val FIRST_TURN = 2
    }
}
