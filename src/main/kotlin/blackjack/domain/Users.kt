package blackjack.domain

data class Users(val users: List<User>) {
    val userCards: Map<User, Cards>
        get() {
            return users.associateBy({ it }, { it.cards })
        }
}
