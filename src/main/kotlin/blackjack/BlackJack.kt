package blackjack

object BlackJack {
    fun start(playerNames: List<String>, deck: Deck): List<Player> {
        return playerNames.map { Player(PlayerName(it), Cards(deck.deal())) }
    }
}