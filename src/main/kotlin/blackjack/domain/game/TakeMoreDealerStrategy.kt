package blackjack.domain.game

interface TakeMoreDealerStrategy {

    fun canBeTakeOneCard(score: Int): Boolean
}
