package blackjack.domain.player

import blackjack.domain.InputInterface
import blackjack.domain.OutputInterface
import blackjack.domain.Score
import blackjack.domain.card.Card

/**
 * 딜러
 * Created by Jaesungchi on 2022.06.15..
 */
class Dealer(initCards: List<Card>) : Player(DEALER_NAME, initCards) {

    constructor(vararg initCards: Card) : this(initCards.asList())

    override fun isReadyToHit(input: InputInterface, output: OutputInterface): Boolean {
        if (isOverHitScore()) return false
        output.drawDealerHitMessage()
        return true
    }

    override fun afterHit(output: OutputInterface) {}

    private fun isOverHitScore(): Boolean {
        return cards.getScore() >= HIT_SCORE
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private val HIT_SCORE = Score(17)
    }
}
