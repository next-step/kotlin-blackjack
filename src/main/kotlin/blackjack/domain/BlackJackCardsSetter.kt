package blackjack.domain

fun interface BlackJackCardsSetter {
    fun shuffle(cards: MutableList<BlackJackCard>)
}
