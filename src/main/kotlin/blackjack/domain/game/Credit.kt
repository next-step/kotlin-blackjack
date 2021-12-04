package blackjack.domain.game

import java.math.BigDecimal

@JvmInline
value class Credit(val value: BigDecimal) {

    operator fun minus(credit: Credit): Credit {
        return Credit(this.value.subtract(credit.value))
    }

    fun receiveBlackJackCredit(): Credit {
        return Credit(this.value.multiply(BigDecimal.valueOf(BLACK_JACK_RATE)).setScale(ROUND_SCALE))
    }

    fun receiveCredit(): Credit {
        return Credit(this.value.add(this.value))
    }

    fun subtractBlackJack(credit: Credit): Credit {
        return Credit(
            this.value.subtract(
                credit.value.times(BigDecimal.valueOf(BLACK_JACK_RATE)).setScale(ROUND_SCALE)
            )
        )
    }

    fun receiveCredit(credit: Credit): Credit {
        return Credit(this.value + credit.value)
    }

    companion object {
        private const val BLACK_JACK_RATE = 1.5
        private const val ROUND_SCALE = 0

        fun from(value: Int): Credit {
            return Credit(BigDecimal(value))
        }

        fun from(value: Double): Credit {
            return Credit(BigDecimal(value))
        }
    }
}
