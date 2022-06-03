package blackjack.model.player

interface HitDecisionMaker {
    fun doYouWantToHit(player: Player): Boolean
}
