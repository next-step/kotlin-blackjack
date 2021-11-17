package blackjack.model

@JvmInline
value class Names private constructor(private val names: List<Name>) {

    fun toList(): List<Name> = names

    companion object {
        fun of(names: List<Name>) = Names(names)

        fun from(vararg names: String) = Names(names.map { Name.valueOf(it) })
    }
}
