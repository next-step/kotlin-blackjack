package blackjack.domain

class BlackJack(players: List<Player>) {

    constructor(vararg player: Player) : this(player.toList())

    private val playerGroup = PlayerGroup(players)
    val deck = CardDeck()
    val dealer = playerGroup.dealer
    val players = playerGroup.players

    init {
        drawInitialCards()
    }

    private fun drawInitialCards() {
        drawInitialDealerCards()
        drawInitialPlayerCards()
    }

    private fun drawInitialDealerCards() {
        val cards = deck.drawMany(INITIAL_DRAW_COUNT)
        dealer.takeCards(cards)
    }

    private fun drawInitialPlayerCards() {
        players.forEach {
            val cards = deck.drawMany(INITIAL_DRAW_COUNT)
            it.takeCards(cards)
        }
    }

    fun drawAnotherCard(player: Player) {
        val card = deck.drawOne()
        player.takeCards(listOf(card))
    }

    fun getWinners(): List<Player> {
        return playerGroup.getWinners()
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
    }
}
