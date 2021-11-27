package blackjack.domain.game

import java.math.BigDecimal

@JvmInline
value class Credit(val value: BigDecimal) {

    fun add(value: Int): Credit {
        return Credit(this.value.add(BigDecimal(value)))
    }

    fun subtract(value: Int): Credit {
        return Credit(this.value.subtract(BigDecimal(value)))
    }

    fun subtract(credit: Credit): Credit {
        return Credit(this.value.subtract(credit.value.times(BigDecimal.valueOf(2))))
    }

    fun receiveCredit(): Credit {
        return Credit(this.value.multiply(BigDecimal.valueOf(1.5)))
    }

    fun subtractDealer(credit: Credit): BigDecimal {
        return this.value.subtract(credit.value.times(BigDecimal.valueOf(1.5)))
    }

    fun multiply(value: Double): Credit {
        return Credit(this.value.times(BigDecimal(value)))
    }

    companion object {
        fun from(value: Int): Credit {
            return Credit(BigDecimal(value))
        }
    }
}
