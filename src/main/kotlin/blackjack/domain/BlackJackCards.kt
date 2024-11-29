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
        if (sums.any { it <= 21 }) {
            return sums.filter { it <= 21 }.max()
        }

        return -1
    }

    companion object {
        private val BlackJack_WIN_CARD_SUM = 21
    }
}
