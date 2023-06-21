package blackjack.vo

import blackjack.Player

class PlayerVO(
    val name: String,
    val cards: List<CardVO>
) {
    companion object {
        operator fun invoke(player: Player): PlayerVO {
            return PlayerVO(
                player.name,
                player.cards.map {
                    CardVO(
                        DenominationVO(it.denomination),
                        SuitVO(it.suit),
                    )
                }
            )
        }
    }
}
