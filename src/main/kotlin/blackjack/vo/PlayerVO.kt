package blackjack.vo

import blackjack.Player

class PlayerVO(
    val name: String,
    val cards: List<CardVO>,
) {
    companion object {
        fun of(player: Player): PlayerVO {
            return PlayerVO(
                player.name,
                player.cards.map {
                    CardVO(
                        DenominationVO.of(it.denomination),
                        SuitVO.of(it.suit),
                    )
                }
            )
        }
    }
}
