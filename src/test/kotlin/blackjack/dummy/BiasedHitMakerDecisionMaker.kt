package blackjack.dummy

import blackjack.model.CardDistributor
import blackjack.model.card.State.Running
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player

// 편한 Hit 결정 : 특정 플레이어만 Hit 기회를 줌
class BiasedHitMakerDecisionMaker(potentialWinnerName: String) :
    BiasedEnvironment(potentialWinnerName),
    HitDecisionMaker {
    override fun shouldHit(player: Player, cardDistributor: CardDistributor): Boolean {
        return (player.isPotentialWinner() && (player.state is Running))
    }
}
