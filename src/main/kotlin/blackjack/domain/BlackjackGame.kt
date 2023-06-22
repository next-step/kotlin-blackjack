package blackjack.domain

class BlackjackGame(val players: List<Player>, val deck: CardDeck) {

    fun initPlayers() {
        players.forEach { player ->
            val cards = deck.getRandomCards(INIT_CARD_COUNT)
            player.cards.initCards(cards)
        }
    }

    fun shareCard(player: Player) {
        val card = deck.getRandomCards(SHARE_CARD_COUNT).first()
        player.receiveCard(card)
    }

    companion object {
        private const val SHARE_CARD_COUNT = 1
        private const val INIT_CARD_COUNT = 2
    }
}
