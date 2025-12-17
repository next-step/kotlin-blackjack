package domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    override fun toString(): String {
        return cards.joinToString(", ")
    }

    fun cards(): List<Card> {
        return cards.toList()
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return calculateScoreTreatAceAsOne() > BLACKJACK_SCORE
    }

    /**
     * 카드 합산 점수 계산
     * A는 1 또는 11로 계산, 11로 계산했을 때 21을 초과하면 1로 계산
     * player 한테 이게 가장 유리하다.
     */
    fun calculateScore(): Int {
        var total = 0
        var aceCount = 0

        for (card in cards) {
            total +=
                when (card.rank) {
                    Rank.ACE -> {
                        aceCount++
                        11
                    }
                    else -> card.rank.value
                }
        }

        while (total > BLACKJACK_SCORE && aceCount > 0) {
            total -= 10
            aceCount--
        }

        return total
    }

    /**
     * ace 를 무조건 1로 판단하는 버전
     */
    fun calculateScoreTreatAceAsOne(): Int {
        var total = 0

        for (card in cards) {
            total +=
                when (card.rank) {
                    Rank.ACE -> 1
                    else -> card.rank.value
                }
        }

        return total
    }
}
