package blackjack.domain.player

import blackjack.domain.card.CardsDeck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {

    fun getAllPlayers(): List<Participant> {
        return listOf(dealer.dealer) + players.map { it.player }
    }

    companion object {
        private const val FIRST_CARD_DIVIDE_COUNT = 2

        fun build(
            dealer: Dealer,
            players: List<Player>,
            cardsDeck: CardsDeck,
        ): Participants {
            val allPlayers = listOf(dealer) + players

            allPlayers.forEach { player ->
                repeat(FIRST_CARD_DIVIDE_COUNT) {
                    player.addCard(cardsDeck.divide())
                }
            }

            return Participants(
                dealer = dealer,
                players = players,
            )
        }
    }
}
