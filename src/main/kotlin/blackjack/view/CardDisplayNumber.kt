package blackjack.view

import blackjack.domain.CardNumber

object CardDisplayNumber {
    private const val INDEX_OF_DISPLAY_NAME = 0
    private const val ACE_DISPLAY_NAME = 'A'

    val CardNumber.displayName: Char
        get() {
            if (this == CardNumber.ACE) {
                return ACE_DISPLAY_NAME
            }

            if (this == CardNumber.JACK || this == CardNumber.QUEEN || this == CardNumber.KING) {
                return this.name[INDEX_OF_DISPLAY_NAME]
            }

            return this.value.toChar()
        }
}
