package blackjack.domain

class BlackJackPlayer(_cards: List<BlackJackCard>) {
    val cards = _cards.toMutableList()

    init {
        require(_cards.size == DEFAULT_CARD_NUMBER) { "플레이어는 처음에 2장만 가지고 시작해야해요" }
    }

    companion object {
        private val DEFAULT_CARD_NUMBER = 2
    }
}
