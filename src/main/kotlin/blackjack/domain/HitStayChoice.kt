package blackjack.domain

enum class HitStayChoice(val choice: String) {
    HIT("y"),
    STAY("n"),
    ;

    companion object {
        fun from(choice: String): HitStayChoice {
            return entries.find { it.choice == choice }
                ?: throw IllegalArgumentException("y 또는 n 중 하나를 입력해주세요.")
        }
    }
}
