package blackjack.domain.game.strategy

interface TakeMoreDealerStrategy {

    fun canBeTakeOneCard(score: Int): Boolean = false
}
