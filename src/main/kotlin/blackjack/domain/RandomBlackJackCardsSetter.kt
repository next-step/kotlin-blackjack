package blackjack.domain

class RandomBlackJackCardsSetter : BlackJackCardsSetter {
    override fun shuffle(cards: MutableList<BlackJackCard>) {
        cards.shuffled()
    }
}
