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
        return cards.fold(listOf(0)) { scores, card ->
            scores.flatMap { score ->
                card.number.addValueLessThanEqualBlackJackNumber(score)
            }
        }.filter { it in 1..BLACKJACK_NUMBER }.maxOrNull() ?: -1
    }

    fun isBlackJack(): Boolean {
        return getCardsBestSum().equals(BLACKJACK_NUMBER) && cards.size == 2
    }

    companion object {
        const val BLACKJACK_NUMBER = 21

        fun byDeck(blackJackDeck: BlackJackDeck): BlackJackPlayerCards {
            return BlackJackPlayerCards(mutableListOf(blackJackDeck.draw(), blackJackDeck.draw()))
        }
    }
}
