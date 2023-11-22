package step2.blackjack.model

@JvmInline
value class Names private constructor(private val names: List<Name>): List<Name> by names {

    companion object {
        fun from(names: List<Name>): Names = Names(names)
    }
}