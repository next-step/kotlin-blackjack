package blackjack.domain

class RandomBlackJackCardShapeGenerator : BlackJackCardShapeGenerator {
    override fun getShape(): BlackJackCardShape {
        return BlackJackCardShape.entries.shuffled().first()
    }
}
