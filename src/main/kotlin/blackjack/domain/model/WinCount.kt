package blackjack.domain.model

@JvmInline
value class WinCount private constructor(val value: Int) {
    companion object {
        fun from(count: Int): WinCount = WinCount(count)
    }
}
