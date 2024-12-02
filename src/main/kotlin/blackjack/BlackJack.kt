package blackjack

object BlackJack {
    private const val DEAL_CARD_COUNT = 2

    fun start(playerNames: List<String>, deck: Deck): List<Player> {
        return playerNames.map { Player(PlayerName(it), Cards(deck.pick(DEAL_CARD_COUNT))) }
    }
}