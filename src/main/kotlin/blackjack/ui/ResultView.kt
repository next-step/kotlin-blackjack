package blackjack.ui

import blackjack.domain.card.Card
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerResult

object ResultView {
    private const val REQUEST_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입하세요.(쉼표 기준으로 분리)"
    private const val RESULT_FOR_INIT_CARDS = "에게 2장을 나누었습니다."
    private const val ASK_TAKE_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예 y, 아니오는 n)"
    private const val ASK_AMOUNT_OF_BET = "의 배팅 금액은?"
    private const val PLAYER_RESULT_FORMAT = "%s카드: %s - 결과: %s"
    private const val PLAYER_HAND_FORMAT = "%s카드: %s"
    private const val USER_NAME_SEPARATOR = ", "
    private const val CARD_SEPARATOR = ", "
    private const val DEALER_HIT_RESULT = "딜러는 16이하라 %d장의 카드를 더 받았습니다."
    private const val TOTAL_RESULT_TITLE = "## 최종 수익"
    private const val BET_RESULT = "%s: %d"
    private const val TOTAL_RESULT_WINS_AND_LOSES = "%s: %d승 %d패"

    fun printRequestPlayerNames() = println(REQUEST_PLAYER_NAMES)

    fun printAskTakeMoreCard(player: Player) = println("${player.getName()}$ASK_TAKE_MORE_CARD")

    fun printAddCardsForInit(name: String) = println("$name$RESULT_FOR_INIT_CARDS")

    fun printAskAmountOfBet(name: PlayerName) = println("$name$ASK_AMOUNT_OF_BET")

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
        val name = result.getName()
        val bet = result.betResult.value
        println(BET_RESULT.format(name, bet))
    }

    private fun Card.cardToString(): String = "$suit$cardName"

    private fun cardListToString(cardList: List<Card>): String =
        cardList.joinToString(CARD_SEPARATOR) { it.cardToString() }
}
