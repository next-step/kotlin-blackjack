package blackjack.domain.player

class UserFactory {
    companion object {
        private const val DELIMITERS = ","

        fun create(input: String): Users {
            val names = input.split(DELIMITERS)
            validateDuplicatedName(names)
            return Users(listOf(Dealer()) + names.map { Player(UserName(it.trim())) })
        }

        private fun validateDuplicatedName(names: List<String>) {
            val toSet = names.toSet()
            require(names.size == toSet.size) { "플레이어끼리 이름이 중복될 수 없습니다." }
        }
    }
}
