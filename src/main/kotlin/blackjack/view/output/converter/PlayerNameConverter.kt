package blackjack.view.output.converter

import blackjack.domain.PlayerName

object PlayerNameConverter : OutputConverter<PlayerName> {
    override fun convert(printable: PlayerName): String {
        return "${printable.value} 카드"
    }
}
