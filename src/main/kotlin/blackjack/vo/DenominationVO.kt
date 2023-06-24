package blackjack.vo

import blackjack.domain.Denomination

class DenominationVO(
    val symbol: String,
) {
    companion object {
        fun of(denomination: Denomination): DenominationVO {
            return when (denomination) {
                Denomination.ACE -> DenominationVO("1")
                Denomination.TWO -> DenominationVO("2")
                Denomination.THREE -> DenominationVO("3")
                Denomination.FOUR -> DenominationVO("4")
                Denomination.FIVE -> DenominationVO("5")
                Denomination.SIX -> DenominationVO("6")
                Denomination.SEVEN -> DenominationVO("7")
                Denomination.EIGHT -> DenominationVO("8")
                Denomination.NINE -> DenominationVO("9")
                Denomination.TEN -> DenominationVO("10")
                Denomination.JACK -> DenominationVO("J")
                Denomination.QUEEN -> DenominationVO("Q")
                Denomination.KING -> DenominationVO("K")
            }
        }
    }
}
