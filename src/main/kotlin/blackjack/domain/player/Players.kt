package blackjack.domain.player

import blackjack.Fetcher
import blackjack.Printer
import blackjack.common.PlayerDecision
import blackjack.common.PlayerSummary
import blackjack.domain.PlayerTurn
import blackjack.domain.card.CardDeck

@JvmInline
value class Players(private val list: List<Player>) {
    init {
        require(list.isNotEmpty()) { ONE_OR_MORE_ELEMENTS_REQUIRED }
    }

    fun play(deck: CardDeck, getPlayerDecision: Fetcher<String, PlayerDecision>, printPlayerSummary: Printer<PlayerSummary>): List<PlayerResult> {
        return list.map {
            PlayerTurn(it).play(deck, getPlayerDecision, printPlayerSummary)
        }
    }

    fun toPlayerSummaries(): List<PlayerSummary> {
        return list.map { PlayerSummary(it) }
    }

    companion object {
        private const val ONE_OR_MORE_ELEMENTS_REQUIRED = "최소 1개 이상의 플레이어를 입력해주세요."
    }
}
