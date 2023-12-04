package blackjack.domain

class Participants(
    val dealer: Dealer,
    val players: Players
) {
    fun receiveInitialCards(initialCards: () -> Cards) {
        players.receiveInitialCards(initialCards)
        dealer.receiveInitialCards(initialCards.invoke())
    }

    fun playGameByPlayer(willHit: (Player) -> Boolean, card: () -> Card): Player? {
        val player = players.withHit().firstOrNull() ?: return null
        val playerWillHit = willHit.invoke(player)
        if (playerWillHit) {
            player.receiveCard(card.invoke())
        } else {
            player.turnStand()
        }
        return player
    }

    fun playGameByDealer(card: () -> Card): Dealer {
        if (dealer.canReceiveOneMoreCard()) dealer.receiveCard(card.invoke())
        return dealer
    }
}
