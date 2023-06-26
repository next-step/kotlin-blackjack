package blackjack.domain

class BlackjackGame(
    val player: List<Player>,
    val deck: CardDeck
) {

    fun initPlayer() {
        player.forEach { player ->
            initCard(player, INIT_CARD_COUNT)
            player.cards.card.forEach {
                player.updateScore(it)
                player.updateStatus()
            }
        }
    }

    private fun initCard(player: Player, count: Int = INIT_CARD_COUNT) {
        val randomCards = deck.getRandomCard(count)
        player.cards.initCard(randomCards)
    }

    fun giveCard(player: Player, count: Int = GIVE_CARD_COUNT) {
        val randomCards = deck.getRandomCard(count).first()
        player.getCard(randomCards)
    }

    companion object {
        const val INIT_CARD_COUNT = 2
        const val GIVE_CARD_COUNT = 1
    }
}
