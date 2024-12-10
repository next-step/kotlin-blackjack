package blackjack

class Player(name: String, initialCards: List<Card>) : Participant(name, initialCards) {
    var betAmount: Money = Money.ZERO
        private set

    fun bet(betAmount: Long) {
        require(betAmount > 0) { "베팅 금액은 1 이상이어야 한다: betAmount=$betAmount" }
        this.betAmount = Money(betAmount)
    }
}
