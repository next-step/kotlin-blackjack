package blackjack.view.output.converter

import blackjack.domain.MatchStatus

object MatchStatusConverter : OutputConverter<MatchStatus> {
    override fun convert(printable: MatchStatus): String {
        return when (printable) {
            is MatchStatus.Dealer -> "${printable.win}승 ${printable.push}무 ${printable.lose}패"
            is MatchStatus.Player -> when (printable) {
                is MatchStatus.Win -> "승"
                is MatchStatus.Push -> "무"
                is MatchStatus.Lose -> "패"
                else -> ""
            }
        }
    }
}
