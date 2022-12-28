package blackjack.domain

class Users(val values: List<User> = emptyList()) {
    fun drawInitCards(deck: Deck): Users {
        return Users(values.map { it.drawInitialCards(deck) })
    }

    fun calculateResult(dealer: Dealer): List<PlayerResult> {
        return values.map {
            PlayerResult.from(it, dealer)
        }
    }
}
