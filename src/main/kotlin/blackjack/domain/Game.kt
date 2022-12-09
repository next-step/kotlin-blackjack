package blackjack.domain

const val INITIAL_CARD_COUNT = 2

class Game(val players: Players, val dealer: Dealer = Dealer(Deck().cards)) {

    init {
        initialCard()
    }

    fun getPlayers(): List<Player> = players.list

    private fun initialCard() {
        dealer.shuffle()

        players.list.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.cards.add(dealer.divide())
            }
        }
    }
}
