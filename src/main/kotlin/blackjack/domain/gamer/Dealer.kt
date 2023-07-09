package blackjack.domain.gamer

import blackjack.domain.gamer.GameRecordType.DRAW
import blackjack.domain.gamer.GameRecordType.LOSE
import blackjack.domain.gamer.GameRecordType.WIN

class Dealer : BlackJackGamer(name = DEALER_NAME) {
    private var winRecord = (WIN to 0)
    private var loseRecord = (LOSE to 0)
    private var drawRecord = (DRAW to 0)

    override fun getGamerType(): GamerType {
        return GamerType.DEALER
    }

    override fun proceedGameRecord(gameRecordType: GameRecordType) {
        when (gameRecordType) {
            WIN -> winRecord = Pair(WIN, winRecord.second + 1)
            LOSE -> loseRecord = Pair(LOSE, loseRecord.second + 1)
            DRAW -> drawRecord = Pair(DRAW, drawRecord.second + 1)
        }
    }

    fun getTotalGameRecord(): List<Pair<GameRecordType, Int>> {
        return listOf(winRecord, loseRecord, drawRecord)
    }

    fun winMoney(winMoney: Int) {
        super.takeMoney(winMoney)
    }

    fun loseMoney(loseMoney: Int) {
        super.takeMoneyOut(loseMoney)
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val CONDITION_TO_DEALER_DRAW_CARD = 16
    }
}
