package blackjack.domain

data class Users(val users: List<User>) {
    val userCards: Map<User, Cards>
        get() {
            return users.associateBy({ it }, { it.cards })
        }

    fun cardReceivePossibleUsers(): List<User> {
        return users.filter { user -> !user.isDeckComplete }
    }
}
