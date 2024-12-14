package blackjack.adapter

import blackjack.domain.HitStayChoice
import blackjack.domain.PlayerName

interface BlackjackInputAdapter {
    fun fetchPlayerNames(): List<PlayerName>

    fun fetchBettingMoney(playerName: String): Int

    fun fetchMoreCard(playerName: PlayerName): HitStayChoice
}
