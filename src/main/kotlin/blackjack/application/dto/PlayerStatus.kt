package blackjack.application.dto

import blackjack.domain.card.Card
import blackjack.domain.player.vo.Name

data class PlayerStatus(
    val name: Name,
    val cards: List<Card>
)

data class PlayerStatuses(
    val statuses: List<PlayerStatus>
)
