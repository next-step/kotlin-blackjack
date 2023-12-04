package blackjack.domain.model

@JvmInline
value class Names private constructor(private val values: List<Name>) : List<Name> by values {

    init {
        require(values.size in MIN_SIZE..MAX_SIZE) {
            "이름들의 사이즈는 $MIN_SIZE~${MAX_SIZE}이여야 합니다."
        }
    }

    companion object {

        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 10

        fun from(names: List<Name>): Names = Names(names)
    }
}
