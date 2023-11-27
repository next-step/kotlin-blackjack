package blackjack.view

import blackjack.domain.component.BlackjackGame
import blackjack.domain.model.PlayerInfo

class BlackjackResultView {
    fun printGameInitialStatus(playerInfos: List<PlayerInfo>) {
        println("${playerInfos.createPlayerNamesString()}에게 ${BlackjackGame.INITIAL_CARD_COUNT}장의 나누었습니다.")
        printPlayerInfos(playerInfos)
    }

    fun printPlayerInfos(playerInfos: List<PlayerInfo>) {
        playerInfos.forEach { printPlayerInfo(it) }
        println()
    }

    fun printPlayerInfo(playerInfo: PlayerInfo) {
        println("${playerInfo.name} 카드: ${playerInfo.createCardsString()}")
    }

    private fun List<PlayerInfo>.createPlayerNamesString(): String {
        return joinToString(CARDS_STRING_DELIMITER) { it.name }
    }

    private fun PlayerInfo.createCardsString(): String {
        return this.cards.joinToString(CARDS_STRING_DELIMITER)
    }

    companion object {
        private const val CARDS_STRING_DELIMITER = ", "
    }
}
