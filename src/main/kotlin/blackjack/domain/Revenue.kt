package blackjack.domain

@JvmInline
value class Revenue(val value: Int) {
    fun reverse(): Revenue = Revenue(value * -1)
}
