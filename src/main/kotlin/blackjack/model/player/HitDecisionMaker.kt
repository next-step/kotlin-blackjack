package blackjack.model.player

interface HitDecisionMaker {
    fun shouldHit(player: Player): Boolean
}
