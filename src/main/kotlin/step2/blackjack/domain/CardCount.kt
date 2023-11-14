package step2.blackjack.domain

import step2.blackjack.model.CardList

object CardCount {
    /**
     * 카드 리스트 가장 작게 나올수 있는 총 숫자의 합
     * */
    fun CardList.getMinTotalCount(): Int = cardList.sumOf { it.cardNumber.cardNumber.second }

    /**
     * 특정 숫자에 가장 가깝게 나올수 있는 총 숫자의 합
     * */
    fun CardList.getAroundCount(targetNumber: Int): Int {
        val (aceList, normalList) = cardList.partition { it.cardNumber.cardNumber.third != 0 }
        val normalNumberSum = normalList.sumOf { it.cardNumber.cardNumber.second }
        val aroundCount = aceList.foldIndexed(normalNumberSum) { index, acc, card ->
            if (acc + (card.cardNumber.cardNumber.third) + aceList.size - index - 1 <= targetNumber) {
                acc + card.cardNumber.cardNumber.third
            } else {
                acc + card.cardNumber.cardNumber.second
            }
        }

        return aroundCount
    }
}