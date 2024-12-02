package blackjack.domain

class BlackJackDealer(
    name: String,
    blackJackPlayerCards: BlackJackPlayerCards,
) : BlackJackPlayer(name, blackJackPlayerCards) {
    override fun isDrawPossible(): Boolean {
        return blackJackPlayerCards.getCardsBestSum() <= 16
    }
}
