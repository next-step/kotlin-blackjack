package blackjack.domain

class Game(val cardDeck: CardDeck = CardDeck()) {

    fun drawTwoCards(player: Player) {
        draw(player, INIT_DRAW_CARD_COUNT)
    }

    fun draw(player: Player, drawCount: Int = 1) {
        repeat(drawCount) {
            player.receive(cardDeck.draw())
        }
    }
}
