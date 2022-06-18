package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.view.OutputInterface

/**
 * 딜러
 * Created by Jaesungchi on 2022.06.15..
 */
class Dealer(initCard: Card) : User(DEALER_NAME, listOf(initCard)) {

    fun getWinAndLose(users: List<User>): Pair<Int, Int> {
        val winSize = users.filter { isWin(it) }.size
        return Pair(winSize, users.size - winSize)
    }

    fun isOverHitScore(): Boolean {
        return cards.getScore().value >= HIT_SCORE
    }

    override fun hitStage(deck: Deck, output: OutputInterface) {
        while (!isOverHitScore()) {
            output.printDealerHitMessage()
            hit(deck.takeCard())
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_SCORE = 17
    }
}
