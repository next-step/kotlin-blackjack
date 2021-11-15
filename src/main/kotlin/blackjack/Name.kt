package blackjack

@JvmInline
value class Name private constructor(private val value: String) {

    init {
        require(value.isNotEmpty())
    }

    override fun toString(): String = value

    companion object {
        fun valueOf(value: String): Name = Name(value)
    }
}
