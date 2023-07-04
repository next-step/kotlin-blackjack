package blackjack.domain.user

import blackjack.domain.card.Deck
import blackjack.domain.status.ResultStatus
import java.lang.IllegalStateException

class Dealer(private val deck: Deck = Deck.create(), name: String = "dealer") : Player(name) {

    private var resultStatuses: MutableList<ResultStatus> = mutableListOf()

    fun giveCardTo(player: Player, cardCount: Int = 1) {
        repeat(cardCount) { player.cards.addCard(deck.getNextCard()) }
        player.updateStatus()
    }

    fun giveCardIfPlayerWantHit(player: Player) {
        if (player.wantHit()) {
            giveCardTo(player)
        }
    }

    fun drawCardBySelfIfPointUnder(dealerDrawThresholdPoint: Int): Boolean {
        if (cards.getOptimizedPoint() <= dealerDrawThresholdPoint) {
            giveCardTo(this)
            return true
        }
        return false
    }

    fun judgeGameResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach {
            player ->
            val resultStatus = ResultStatus.values()
                .firstOrNull { it.isMatch(player, this) }
                ?: throw IllegalStateException()
            player.updateResultStatus(resultStatus)
            resultStatuses.add(resultStatus)
        }
    }

    override fun getFinalResult(): FinalResult {
        val winCount = resultStatuses.filter { resultStatus -> resultStatus.isDealerWin }.count()
        val loseCount = resultStatuses.size - winCount
        return FinalResult(winCount, loseCount)
    }
}
