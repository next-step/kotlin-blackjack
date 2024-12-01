package blackjack.domain

class Dealer(
    val deck: Deck = Deck(),
) {
    infix fun initCardTo(player: Player): Player {
        repeat(INIT_CARD_COUNT) {
            player receive deck.draw()
        }
        return player
    }

    infix fun giveCardTo(player: Player) {
        player receive deck.draw()
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
