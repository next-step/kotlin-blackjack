package blackjack.common

@JvmInline
value class NonEmptyList<T>(val list: List<T>) {
    init {
        require(list.isNotEmpty()) { ONE_OR_MORE_ELEMENTS_REQUIRED }
    }

    companion object {
        private const val ONE_OR_MORE_ELEMENTS_REQUIRED = "최소 1개 이상의 값을 입력해주세요."
    }
}

fun <T> List<T>.toNonEmptyList(): NonEmptyList<T> = NonEmptyList(this)
