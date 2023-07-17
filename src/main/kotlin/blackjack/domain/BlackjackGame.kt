package blackjack.domain

import blackjack.domain.player.Challengers

typealias OnHitDecided = (Player) -> Boolean
typealias OnAfterInitializeHands = (Challengers, Dealer) -> Unit
typealias OnAfterChallengerHit = (Player) -> Unit
typealias OnAfterDealerHit = () -> Unit

/**
 * ### 블랙잭 게임의 흐름을 제어하는 역할을 합니다
 */
class BlackjackGame(
    val challengers: Challengers,
    val dealer: Dealer = Dealer(),
    private val onHitDecided: OnHitDecided,
    private val onAfterInitializeHands: OnAfterInitializeHands = { c, d -> },
    private val onAfterChallengerHit: OnAfterChallengerHit = {},
    private val onAfterDealerHit: OnAfterDealerHit = {},
    private val cardSet: CardSet = CardSet(),
) {
    fun run() {
        dealInitialHand()
        processChallengersTurn()
        processDealerTurn()
    }

    private fun dealInitialHand() {
        repeat(2) {
            challengers.forEach { player ->
                player.receive(cardSet.pop())
            }
            dealer.receive(cardSet.pop())
        }
        onAfterInitializeHands(challengers, dealer)
    }

    private fun processChallengersTurn() {
        challengers.forEach {
            processChallenger(it)
        }
    }

    private fun processChallenger(it: Challenger) {
        while (it.canHit) {
            if (onHitDecided(it)) {
                it.receive(cardSet.pop())
                onAfterChallengerHit(it)
            } else {
                it.stay()
            }
        }
    }

    private fun processDealerTurn() {
        while (dealer.canHit) {
            dealer.receive(cardSet.pop())
            onAfterDealerHit()
        }
    }
}
