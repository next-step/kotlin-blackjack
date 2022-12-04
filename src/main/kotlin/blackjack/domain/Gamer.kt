package blackjack.domain

data class Gamer(val name: String, val cardList: List<Card>) {

    fun calculate(isAceEleven: Boolean = false): Int {
        val totalScore = cardList.sumOf { card ->
            card.toInt(isAceEleven)
        }
        return totalScore
    }
}
