package blackjack.view

enum class PlayerCommands(val command: String) {
    HIT("y"),
    STAY("n"), ;

    companion object {
        fun findByCommand(value: String): PlayerCommands {
            return entries.firstOrNull { it.command == value }
                ?: throw IllegalArgumentException("지원하지 않는 커맨드입니다.")
        }
    }
}
