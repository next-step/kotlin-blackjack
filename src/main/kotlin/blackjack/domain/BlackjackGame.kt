package blackjack.domain

import blackjack.ui.model.PlayerWinTypes

class BlackjackGame(val players: Players, dealerCards: Set<Card> = emptySet()) {

    private val cardPack = CardPack()
    val dealer = Dealer(dealerCards)

    val addedCardNumberOfDealer: Int
        get() = dealer.cardSize - FIRST_GIVEN_CARD_SIZE

    fun giveTwoCardsToAllPlayers() {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.giveToAllPlayers(cardPack)
            dealer.takeCard(cardPack.poll())
        }
    }

    fun giveCard(player: Player, hasAccepted: Boolean) {
        if (hasAccepted) {
            player.takeCard(cardPack.poll())
        }
    }

    fun giveCardsToDealer() {
        while (dealer.isUnderSixteen) {
            dealer.takeCard(cardPack.poll())
        }
    }

    fun findPlayerWinTypes(): PlayerWinTypes {
        val winTypeMap = mutableMapOf<String, PlayerWinType>()
        val dealerPoint = dealer.cardPointSum()
        players.forEach { winTypeMap[it.name] = PlayerWinType.findPlayerWinType(it.cardPointSum(), dealerPoint) }
        return PlayerWinTypes(winTypeMap)
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
