package blackjack.view

import blackjack.domain.CardNumber

object CardDisplayNumber {

    val CardNumber.displayName: String
        get() {
            if (this == CardNumber.JACK ||
                this == CardNumber.QUEEN ||
                this == CardNumber.KING ||
                this == CardNumber.ACE
            ) {
                return this.name.firstOrNull().toString()
            }

            return this.value.toString()
        }
}
