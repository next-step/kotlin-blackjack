package blackjack.domain

class BlackJackPlayerCards(private val _cards: MutableList<BlackJackCard> = mutableListOf()) {
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
        return (bestSum in 1..BLACKJACK_NUMBER)
    }

    fun getCardsBestSum(): Int {
        val cardValues = _cards.map { it.number.values }

        val sums =
            cardValues.reduce { acc, list ->
                acc.flatMap { accSum ->
                    list.map { listSum -> accSum + listSum }
                }
            }
        if (sums.any { it <= BLACKJACK_NUMBER }) {
            return sums.filter { it <= BLACKJACK_NUMBER }.max()
        }

        return -1
    }

    fun isBlackJack(): Boolean {
        return getCardsBestSum().equals(BLACKJACK_NUMBER)
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21

        fun byDeck(blackJackDeck: BlackJackDeck): BlackJackPlayerCards {
            return BlackJackPlayerCards(mutableListOf(blackJackDeck.draw(), blackJackDeck.draw()))
        }
    }
}
