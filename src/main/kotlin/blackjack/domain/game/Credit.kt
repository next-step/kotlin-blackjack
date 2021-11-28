package blackjack.domain.game

import java.math.BigDecimal

@JvmInline
value class Credit(val value: BigDecimal) {

    fun add(value: Int): Credit {
        return Credit(this.value.add(BigDecimal(value)))
    }

    fun subtractDealerCredit(credit: Credit): Credit {
        return Credit(this.value.subtract((credit.value)))
    }

    fun subtract(credit: Credit): Credit {
        return Credit(this.value.subtract(credit.value.times(BigDecimal.valueOf(2))))
    }

    fun receiveBlackJackCredit(): Credit {
        return Credit(this.value.multiply(BigDecimal.valueOf(1.5)).setScale(0))
    }

    fun receiveCredit(): Credit {
        return Credit(this.value.add(this.value))
    }

    fun subtractBlackJackDealer(credit: Credit): Credit {
        return Credit(this.value.subtract(credit.value.times(BigDecimal.valueOf(1.5)).setScale(0)))
    }

    fun multiply(value: Double): Credit {
        return Credit(this.value.times(BigDecimal(value)))
    }

    fun receiveCredit(credit: Credit): Credit {
        return Credit(this.value + credit.value)
    }

    companion object {
        fun from(value: Int): Credit {
            return Credit(BigDecimal(value))
        }

        fun from(value: Double): Credit {
            return Credit(BigDecimal(value))
        }
    }
}
