package blackjack.domain

class BlackJackCards(private val _cards: MutableList<BlackJackCard> = mutableListOf()) {
    val cards: List<BlackJackCard>
        get() {
            return _cards.toList()
        }

    fun addCard(card: BlackJackCard) {
        require(isCardNumberSumUnderBlackJackWinCardSum()) { "21을 넘으면 카드를 못뽑아요" }
        _cards.add(card)
    }

    fun isCardNumberSumUnderBlackJackWinCardSum(): Boolean {
        val bestSum = getCardsBestSum()
        return (bestSum in 1..BLACKJACK_WIN_CARD_SUM)
    }

    fun getCardsBestSum(): Int {
        val cardValues = _cards.map { it.number.values }

        val sums =
            cardValues.reduce { acc, list ->
                acc.flatMap { accSum ->
                    list.map { listSum -> accSum + listSum }
                }
            }
        if (sums.any { it <= BLACKJACK_WIN_CARD_SUM }) {
            return sums.filter { it <= BLACKJACK_WIN_CARD_SUM }.max()
        }

        return -1
    }

    companion object {
        private val BLACKJACK_WIN_CARD_SUM = 21
    }
}
