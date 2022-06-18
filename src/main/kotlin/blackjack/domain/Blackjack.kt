package blackjack.domain

class Blackjack(
    val players: List<Player>
) {
    private val deck = Deck.create()

    fun drawFirstCards() {
        players.forEach {
            val cards = deck.draw(INIT_DRAW_COUNT)
            it.addCards(cards)
        }
    }

    fun isDrawable(player: Player): Boolean {
        require(player in players) { "[${player.name} is not blackjack player" }

        return player.getPoints() < BLACKJACK_POINTS
    }

    fun drawCard(player: Player): Card {
        require(player in players) { "[${player.name} is not blackjack player" }
        require(isDrawable(player)) { "[${player.name}]은 21점 이상이라 카드를 받을 수 없습니다." }

        val card = deck.draw()

        player.addCard(deck.draw())

        return card
    }

    companion object {
        private const val INIT_DRAW_COUNT = 2
        private const val BLACKJACK_POINTS = 21
    }
}
