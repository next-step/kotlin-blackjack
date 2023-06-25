package blackjack.domain.model

class Game(private val trump: Trump = Trump(_cards = Trump.defaultTrump().shuffled().toMutableList())) {
    fun getCard(): Card = trump.getCard()
}
