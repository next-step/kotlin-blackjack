package blackjack.domain

class Blackjack(
    val players: List<Player>
) {
    val dealer = Dealer()

    private val users = players.plus(dealer)

    private val deck = Deck.create()

    fun drawFirstCards() {
        check(users.none { it.cards.isNotEmpty() })
        users.forEach {
            val cards = deck.draw(INIT_DRAW_COUNT)
            it.addCards(cards)
        }
    }

    fun isDrawable(player: Player): Boolean {
        require(player in players) { "[${player.name} is not blackjack player" }

        return player.getPoints() < BLACKJACK_POINTS
    }

    fun drawCard(player: Player): Card {
        require(player in users) { "[${player.name}] is not blackjack player" }
        check(player.drawable())

        val card = deck.draw()

        player.addCard(deck.draw())

        return card
    }

    companion object {
        private const val INIT_DRAW_COUNT = 2
        private const val BLACKJACK_POINTS = 21
    }
}
