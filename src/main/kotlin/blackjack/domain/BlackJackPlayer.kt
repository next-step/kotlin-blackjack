package blackjack.domain

class BlackJackPlayer(_cards: List<BlackJackCard>) {
    val cards = _cards.toMutableList()

    init {
        require(_cards.size == DEFAULT_CARD_NUMBER) { "플레이어는 처음에 2장만 가지고 시작해야해요" }
    }

    fun drawCard(blackJackCard: BlackJackCard) {
        require(isCardNumberSumUnder21()) { "21을 넘으면 카드를 못뽑아요" }
        cards.add(blackJackCard)
    }

    fun isCardNumberSumUnder21(): Boolean {
        return cards.sumOf { it.number.values.min() } <= BlackJack_WIN_CARD_SUM
    }

    companion object {
        private val DEFAULT_CARD_NUMBER = 2
        private val BlackJack_WIN_CARD_SUM = 21
    }
}
