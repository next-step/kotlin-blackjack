package blackjack.domain.player

class PlayerFactory {
    companion object {
        private const val DELIMITERS = ","

        fun create(input: String): Players {
            val names = input.split(DELIMITERS)
            validateDuplicatedName(names)
            return Players(names.map { Player(UserName(it)) })
        }

        private fun validateDuplicatedName(names: List<String>) {
            val toSet = names.toSet()
            require(names.size == toSet.size) { "플레이어끼리 이름이 중복될 수 없습니다." }
        }
    }
}
