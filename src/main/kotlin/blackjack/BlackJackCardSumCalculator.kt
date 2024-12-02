package blackjack

interface BlackJackCardSumCalculator {
    fun sum(cards: List<Card>): Int
}
