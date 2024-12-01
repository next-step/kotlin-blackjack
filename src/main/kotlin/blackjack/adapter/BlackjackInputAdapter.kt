package blackjack.adapter

import blackjack.domain.HitStayChoice
import blackjack.domain.PlayerName

interface BlackjackInputAdapter {
    fun fetchPlayerNames(): List<PlayerName>

    fun fetchMoreCard(playerName: String): HitStayChoice
}
