package blackjack.domain

class BlackjackGame(val players: Players, val dealer: Dealer = Dealer()) {

    private val cardPack = CardPack()

    val addedCardNumberOfDealer: Int
        get() = dealer.cardSize - FIRST_GIVEN_CARD_SIZE

    fun giveTwoCardsToAllPlayers() {
        players.giveFirstTwoCardsToAllPlayers(cardPack)
        dealer.takeFirstTwoCards(cardPack.poll(), cardPack.poll())
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
        val dealerPoint = PlayerPoint(dealer.cardPointSum(), dealer.isBlackjack)
        return PlayerWinTypes.of(players, dealerPoint)
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
