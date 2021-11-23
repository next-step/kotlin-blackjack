package blackjack.model

@JvmInline
value class Profit(val amount: Amount) {

    operator fun unaryMinus() = Profit(-amount)

    override fun toString(): String = amount.toString()
}
