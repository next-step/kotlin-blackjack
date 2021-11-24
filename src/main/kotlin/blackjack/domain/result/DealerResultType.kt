package blackjack.domain.result

enum class DealerResultType(val value: String) {
    WIN("승"), PUSH("무"), LOSE("패");

    override fun toString(): String = this.value
}
