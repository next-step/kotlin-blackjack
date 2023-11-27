package blackjack.view

import blackjack.domain.model.PlayerInfo

class BlackjackResultView {
    fun printPlayerInfo(playerInfo: PlayerInfo) {
        println("${playerInfo.name} 카드: ${playerInfo.createCardsString()}")
    }
    private fun PlayerInfo.createCardsString(): String {
        return this.cards.joinToString(CARDS_STRING_DELIMITER)
    }

    companion object {
        private const val CARDS_STRING_DELIMITER = ", "
    }
}