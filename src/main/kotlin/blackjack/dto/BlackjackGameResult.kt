package blackjack.dto

data class BlackjackGameResult(
    val name: String,
    val win: String? = "",
    val draw: String? = "",
    val lose: String? = ""
)
