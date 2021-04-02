package blackjack.domain.player

import blackjack.domain.BettingMoney

class UserFactory {
    companion object {
        private const val DELIMITERS = ","

        fun createUserNames(input: String): UserNames {
            val names = input.split(DELIMITERS)
            return UserNames(names.map { it.trim() })
        }

        fun createUsers(userNames: UserNames, bettingMoneys: List<BettingMoney>): Users {
            val players = userNames.zip(bettingMoneys).map { Player(it.first, it.second) }
            return Users(listOf(Dealer()) + players)
        }
    }
}
