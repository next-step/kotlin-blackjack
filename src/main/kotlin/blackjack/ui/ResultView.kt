package blackjack.ui

import blackjack.domain.card.Card
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerResult

object ResultView {
    private const val REQUEST_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입하세요.(쉼표 기준으로 분리)"
    private const val RESULT_FOR_INIT_CARDS = "에게 2장을 나누었습니다."
    private const val ASK_TAKE_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예 y, 아니오는 n)"
    private const val PLAYER_RESULT_FORMAT = "%s카드: %s - 결과: %s"
    private const val PLAYER_HAND_FORMAT = "%s카드: %s"
    private const val USER_NAME_SEPARATOR = ", "
    private const val CARD_SEPARATOR = ", "
    private const val DEALER_HIT_RESULT = "딜러는 16이하라 %d장의 카드를 더 받았습니다."
    private const val TOTAL_RESULT_TITLE = "## 최종 승패"
    private const val TOTAL_RESULT_WIN = "%s: 승"
    private const val TOTAL_RESULT_LOSE = "%s: 패"
    private const val TOTAL_RESULT_WINS = "%s: %d승"
    private const val TOTAL_RESULT_LOSES = "%s: %d패"
    private const val TOTAL_RESULT_WINS_AND_LOSES = "%s: %d승 %d패"

    fun printRequestPlayerNames() = println(REQUEST_PLAYER_NAMES)

    fun printAskTakeMoreCard(player: Player) = println("${player.getName()}$ASK_TAKE_MORE_CARD")

    fun printAddCardsForInit(names: String) = println("$names$RESULT_FOR_INIT_CARDS")

    fun printPlayerHand(player: Player) = println(
        PLAYER_HAND_FORMAT.format(
            player.getName(),
            player.getCards()
                .joinToString(USER_NAME_SEPARATOR) {
                    it.cardToString()
                }
        )
    )

    fun printDealerHitResult(hitCount: Int) {
        println()
        println(DEALER_HIT_RESULT.format(hitCount))
        println()
    }

    fun printPlayerResult(player: Player) {
        val handValue = player.getMakeableValues().maxOrNull() ?: player.getMaxHandValue()
        println(
            PLAYER_RESULT_FORMAT.format(
                player.getName(),
                cardListToString(player.getCards()),
                handValue
            )
        )
    }

    fun printTotalResultByPlayerResult(resultList: List<PlayerResult>) {
        println()
        println(TOTAL_RESULT_TITLE)
        resultList.forEach(::printTotalResultByPlayerResult)
    }

    private fun printTotalResultByPlayerResult(result: PlayerResult) {
        val wins = result.getWins()
        val loses = result.getLoses()
        val name = result.getName()
        if (wins != 0 && loses != 0)
            printTotalResultWinsAndLoses(name, wins, loses)
        if (wins != 0)
            printTotalResultWins(name, wins)
        if (loses != 0)
            printTotalResultLoses(name, loses)
    }

    private fun printTotalResultWinsAndLoses(name: PlayerName, win: Int, loses: Int) =
        println(TOTAL_RESULT_WINS_AND_LOSES.format(name, win, loses))

    private fun printTotalResultWins(name: PlayerName, win: Int) {
        if (win == 1) {
            println(TOTAL_RESULT_WIN.format(name))
            return
        }
        println(TOTAL_RESULT_WINS.format(name, win))
    }

    private fun printTotalResultLoses(name: PlayerName, loses: Int) {
        if (loses == 1) {
            println(TOTAL_RESULT_LOSE.format(name))
            return
        }
        println(TOTAL_RESULT_LOSES.format(name, loses))
    }

    private fun Card.cardToString(): String = "$suit$cardName"

    private fun cardListToString(cardList: List<Card>): String =
        cardList.joinToString(CARD_SEPARATOR) { it.cardToString() }
}
