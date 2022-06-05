package blackjack.domain

class Dealer(private val deck: Deck) {

    fun giveCard(player: Player) {
        player.addCard(deck.draw())
    }

    fun makePlayer(name: String): Player =
        Player(name).apply {
            repeat(INIT_CARD_COUNT) { giveCard(this) }
        }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
