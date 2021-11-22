package blackjack.domain.player

import blackjack.domain.card.CardsDeck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {

    fun existsBlackjack(): Boolean {
        return getAllPlayers().any { participant -> participant.isBlackjack() }
    }

    fun getReceiveCardStatistics(): List<ReceiveCardStatistics> {
        return getAllPlayers().map { participant ->
            val cardNames = participant.cards.map { card ->
                "${card.denomination.denomination} ${card.pattern}"
            }

            ReceiveCardStatistics(
                playerName = participant.name,
                cardNames = cardNames,
                cardSum = participant.getCardSum(),
            )
        }
    }

    fun getRevenueStatistics(): List<RevenueStatistics> {
        return getAllPlayers().map { participant ->
            RevenueStatistics(
                playerName = participant.name,
                revenue = participant.getRevenue(),
            )
        }
    }

    private fun getAllPlayers(): List<Participant> {
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
