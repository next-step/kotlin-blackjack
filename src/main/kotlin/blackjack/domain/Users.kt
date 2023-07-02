package blackjack.domain

data class Users(val users: List<User>) {
    fun userCards(): Map<User, Cards> {
        val map = mutableMapOf<User, Cards>()
        users.forEach { map[it] = it.cards }
        return map
    }
}
