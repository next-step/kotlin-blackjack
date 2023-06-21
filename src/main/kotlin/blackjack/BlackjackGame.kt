package blackjack

class BlackjackGame(val players: List<Player>, val deck: CardDeck) {

    fun initPlayers() {
        players.forEach { player -> shareCards(player, INIT_CARD_COUNT) }
    }

    fun shareCards(player: Player, count: Int) {
        val randomCards = deck.getRandomCards(count)
        player.cards.addAll(randomCards)
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
