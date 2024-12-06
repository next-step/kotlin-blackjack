package blackjack.domain

data class Card(val number: CardNumber, val shape: CardShape) {
    companion object {
        fun calculateCardValue(cards: List<Card>): Int {
            val nonAceSum = cards
                .filter { it.number != CardNumber.ACE }
                .sumOf { it.number.getPoints() }

            val aceCount = cards.count { it.number == CardNumber.ACE }

            var totalScore = nonAceSum
            repeat(aceCount) {
                if (totalScore + 11 <= 21) {
                    totalScore += 11
                } else {
                    totalScore += 1
                }
            }

            return totalScore
        }
    }
}
