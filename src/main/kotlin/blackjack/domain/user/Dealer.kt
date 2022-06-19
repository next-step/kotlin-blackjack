package blackjack.domain.user

import blackjack.domain.InputInterface
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

/**
 * 딜러
 * Created by Jaesungchi on 2022.06.15..
 */
class Dealer(initCard: Card) : User(DEALER_NAME, listOf(initCard)) {

    fun getWinAndLose(users: List<User>): MatchResults {
        val matchResult = users.map { match(it) }
        return MatchResults(
            matchResult.count { it == Match.WIN },
            matchResult.count { it == Match.DRAW },
            matchResult.count { it == Match.LOSE }
        )
    }

    fun isOverHitScore(): Boolean {
        return cards.getScore().value >= HIT_SCORE
    }

    override fun hitStage(deck: Deck, input: InputInterface, output: OutputInterface) {
        while (!isOverHitScore()) {
            output.drawDealerHitMessage()
            hit(deck.takeCard())
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_SCORE = 17
    }
}
