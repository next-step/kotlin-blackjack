package blackjack.view

import blackjack.domain.component.BlackjackGame
import blackjack.domain.model.PlayerInfo

class BlackjackResultView {
    fun printGameInitialStatus(playerInfos: List<PlayerInfo>) {
        println("${playerInfos.createPlayerNamesString()}에게 ${BlackjackGame.INITIAL_CARD_COUNT}장의 나누었습니다.")
        printPlayerInfos(playerInfos)
    }

    fun printGameResultStatus(playerInfos: List<PlayerInfo>) {
        println("----- 게임 결과")
        printPlayerInfos(playerInfos, true)
    }

    fun printPlayerInfos(playerInfos: List<PlayerInfo>, withScore: Boolean = false) {
        playerInfos.forEach { printPlayerInfo(it, withScore) }
        println()
    }

    fun printPlayerInfo(playerInfo: PlayerInfo, withScore: Boolean = false) {
        val message = "${playerInfo.name} 카드: ${playerInfo.createCardsString()}" + if (withScore) { " 결과 - ${playerInfo.score}" } else { "" }

        println(message)
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
