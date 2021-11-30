package blackjack.ui

import blackjack.domain.card.Card
import blackjack.domain.player.Player

object ResultView {
    private const val REQUEST_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입하세요.(쉼표 기준으로 분리)"
    private const val RESULT_FOR_INIT_CARDS = "에게 2장을 나누었습니다."
    private const val ASK_TAKE_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예 y, 아니오는 n)"
    private const val PLAYER_RESULT_FORMAT = "%s카드: %s - 결과: %s"
    private const val PLAYER_HAND_FORMAT = "%s카드: %s"
    private const val USER_NAME_SEPARATOR = ", "
    private const val CARD_SEPARATOR = ", "

    fun printRequestPlayerNames() = println(REQUEST_PLAYER_NAMES)

    fun printAskTakeMoreCard(player: Player) = println("${player.name}$ASK_TAKE_MORE_CARD")

    fun printAddCardsForInit(names: String) = println("$names$RESULT_FOR_INIT_CARDS")

    fun printPlayerHand(player: Player) = println(
        PLAYER_HAND_FORMAT.format(
            player.name,
            player.getCards()
                .joinToString(USER_NAME_SEPARATOR) {
                    it.cardToString()
                }
        )
    )

    fun printPlayerResult(player: Player) = println(
        PLAYER_RESULT_FORMAT.format(
            player.name,
            cardListToString(player.getCards()),
            player.getHandValues().maxOrNull() ?: 0
        )
    )

    private fun Card.cardToString(): String = "$suit$cardName"
    private fun cardListToString(cardList: List<Card>): String =
        cardList.joinToString(CARD_SEPARATOR) { it.cardToString() }
}
