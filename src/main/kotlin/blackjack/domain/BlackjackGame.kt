package blackjack.domain

class BlackjackGame(
    val player: List<Player>, val deck: CardDeck
) {

    fun initPlayer() {
        player.forEach {
            giveCard(it, INIT_CARD_COUNT)
        }
    }

    private fun giveCard(player: Player, count: Int = INIT_CARD_COUNT) {
        val randomCards = deck.getRandomCard(count)
        player.getCard(randomCards[0])
    }

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}