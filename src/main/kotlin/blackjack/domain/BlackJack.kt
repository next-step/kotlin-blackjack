package blackjack.domain

class BlackJack(val players: Players, val cardDeck: CardDeck) {
    init {
        repeat(INIT_CARD_DRAW_REPEAT) { players.players.forEach { it.addCard(cardDeck.drawCard()) } }
    }

    companion object {
        const val INIT_CARD_DRAW_REPEAT = 2
    }
}
