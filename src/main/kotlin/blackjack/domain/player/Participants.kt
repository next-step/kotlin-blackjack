package blackjack.domain.player

import blackjack.domain.card.CardsDeck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
    cardsDeck: CardsDeck,
) {

    init {
        val allPlayers = listOf(dealer) + players

        allPlayers.forEach { player ->
            repeat(FIRST_CARD_DIVIDE_COUNT) {
                player.addCard(cardsDeck.divide())
            }
        }
    }

    fun getAllPlayers(): List<Participant> {
        return listOf(dealer.dealer) + players.map { it.player }
    }

    companion object {
        private const val FIRST_CARD_DIVIDE_COUNT = 2
    }
}
