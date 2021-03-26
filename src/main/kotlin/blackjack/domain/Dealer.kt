package blackjack.domain

import blackjack.ui.model.PlayerDto
import blackjack.ui.model.PlayerWinTypes

class Dealer(
    private val players: Players,
    private val cardPack: CardPack,
    cards: Set<Card> = emptySet()
) : Player("딜러", cards) {

    init {
        players.addDealerAsPlayer(this)
    }

    fun giveTwoCardsToAllPlayers(): Players {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.giveToAllPlayers(cardPack)
        }
        return players
    }

    fun giveCard(player: Player, hasAccepted: Boolean): Player {
        if (hasAccepted) {
            player.takeCard(cardPack.pickCard())
        }
        return player
    }

    fun takeCardIfUnderSixteen(): Boolean {
        if (this.calculateCardSum() <= DEALER_POINT_TO_TAKE_MORE_CARD) {
            this.takeCard(cardPack.pickCard())
            return true
        }
        return false
    }

    fun findPlayerWinTypes(): PlayerWinTypes {
        val winTypeMap = mutableMapOf<String, PlayerWinType>()
        val dealerPoint = this.calculateCardSum()
        players.filter { it != this }
            .forEach { winTypeMap[it.name] = PlayerWinType.findPlayerWinType(it.calculateCardSum(), dealerPoint) }
        return PlayerWinTypes(winTypeMap)
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
        private const val DEALER_POINT_TO_TAKE_MORE_CARD = 16
    }
}
