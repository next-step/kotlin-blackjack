package blackjack

object BlackJack {
    fun start(playerNames: List<String>, deck: Deck) {
        val players = playerNames.map { Player(PlayerName(it), Cards(deck.deal())) }
    }
}