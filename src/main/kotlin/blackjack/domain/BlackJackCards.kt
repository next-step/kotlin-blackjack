package blackjack.domain

class BlackJackCards(val cards: MutableList<BlackJackCard>) {
    fun addCard(card: BlackJackCard) {
        require(isCardNumberSumUnder21())
        cards.add(card)
    }

    fun isCardNumberSumUnder21(): Boolean {
        return cards.sumOf { it.number.values.min() } <= BlackJack_WIN_CARD_SUM
    }

    fun getCardsBestSum(): Int {
        val cardValues = cards.map { it.number.values }

        val sums =
            cardValues.reduce { acc, list ->
                acc.flatMap { accSum ->
                    list.map { listSum -> accSum + listSum }
                }
            }

        return sums.filter { it <= 21 }.max()
    }

    companion object {
        private val BlackJack_WIN_CARD_SUM = 21
    }
}
