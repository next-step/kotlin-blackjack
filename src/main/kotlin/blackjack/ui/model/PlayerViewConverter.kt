package blackjack.ui.model

import blackjack.domain.player.AbstractPlayer
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer

object PlayerViewConverter {
    fun convert(player: AbstractPlayer): PlayerViewModel = when (player) {
        is GamePlayer -> GamePlayerConverter.convert(player)
        is Dealer -> DealerConverter.convert(player)
        else -> throw IllegalArgumentException("지원하지 않는 타입입니다.")
    }
}
