package blackjack

fun interface CardShuffleStrategy {
    fun shuffle(): List<Card>
}
