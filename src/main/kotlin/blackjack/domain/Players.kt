package blackjack.domain

class Players(val values: List<Player> = emptyList()) {
    fun drawInitCards(deck: Deck): Players {
        return Players(values.map { it.drawInitialCards(deck) })
    }

    fun calculateResult(dealer: Dealer): List<PlayerResult> {
        return values.map {
            PlayerResult.from(it, dealer)
        }
    }
}
